package org.co2dice.mirai.core.bean.effect.module.cost

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import org.co2dice.mirai.core.utils.MathUtils
import org.co2dice.mirai.core.utils.TriEither
import org.co2dice.mirai.core.utils.filterAsInstance
import org.co2dice.mirai.core.utils.fold
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-24-15:29
 * {@code @Message:} cost的包装类
 * cost和target不同，需要将内部获取到的对象变成单个属性而不是一个map或者list。因为诸如thisCardCost这种cost存在不兼容问题。
 * 一张卡无法被作为两个cost的素材。
 **/
@Serializable
sealed interface CostPackage{
    val costs : Map<String,Cost>

    fun check(input : Map<String,Any> = mapOf(),situation: PreActivationSituation) : Boolean{
        return getSelectScope(input, situation).isNotEmpty()
    }

    fun getSelectScope(input : Map<String,Any> = mapOf(), situation: PreActivationSituation)
    : List<Map<String, TriEither<Any, Pair<Int, Any>, Pair<Int, Set<Any>>>>>{
        //返回值根据不同情况分三种any:一对一，map<int,any>:多对一，map<int,set<any>>:多对多
        //首先,将selectors的类型分为三类:一对一，多对一，多对多
        //一对一的不用管，不需要遍历，多对一的当作一个list即可，多对多的当作一个list<set>,将三者分别列出

        if (costs.any { !it.value.check(input, situation) }) return listOf()

        val oneToOne : List<Pair<String,OneToOneSelectionCost<*>>>
        = costs.map { it.toPair() }.toList().filterAsInstance()
        val manyToOne : List<Pair<String,ManyToOneSelectionCost<*>>>
        = costs.map { it.toPair() }.toList().filterAsInstance()
        val manyToMany : List<Pair<String,ManyToManySelectionCost<*>>>
        = costs.map { it.toPair() }.toList().filterAsInstance()


        //先将一对一的转为map<String,Any>
        val otoObj : Map<String,Any>
        = oneToOne.associate { it.first to it.second.getCostScope(input, situation)!! }
        //再将多对一的转为map<String,map<int,any>>
        val mtoObj : Map<String,Map<Int,Any>>
        = manyToOne.associate { it.first to it.second.getCostScope(input, situation) }
        //再将多对多的转为map<String,map<int,set<any>>>
        val mtmObj : Map<String,Map<Int,Set<Any>>>
        = manyToMany.associate { it.first to it.second.getCostScope(input, situation) }

        return MathUtils.generateAllCombinations(
            otoObj,
            mtoObj,
            mtmObj
        )
    }

    fun getSelectScopeJson(input : Map<String,Any> = mapOf(), situation: PreActivationSituation) : JsonElement {
        //返回值根据不同情况分三种any:一对一，map<int,any>:多对一，map<int,set<any>>:多对多
        //首先,将selectors的类型分为三类:一对一，多对一，多对多
        //一对一的不用管，不需要遍历，多对一的当作一个list即可，多对多的当作一个list<set>,将三者分别列出

        if (costs.any { !it.value.check(input, situation) }) return JsonNull

        val oneToOne : List<Pair<String, OneToOneSelectionCost<*>>>
            = costs.map { it.toPair() }.toList().filterAsInstance()
        val manyToOne : List<Pair<String, ManyToOneSelectionCost<*>>>
            = costs.map { it.toPair() }.toList().filterAsInstance()
        val manyToMany : List<Pair<String, ManyToManySelectionCost<*>>>
            = costs.map { it.toPair() }.toList().filterAsInstance()

        //先将一对一的转为map<String,Any>
        val otoObj : Map<String, JsonElement>
            = oneToOne.associate { it.first to it.second.getJsonObj(input,situation).jsonObject }
        //再将多对一的转为map<String,map<int,any>>
        val mtoObj : Map<String,Map<Int, JsonElement>>
            = manyToOne.associate { it.first to it.second.getJsonObj(input, situation) }
        //再将多对多的转为map<String,map<int,set<any>>>
        val mtmObj : Map<String,Map<Int,Set<JsonElement>>>
            = manyToMany.associate { it.first to it.second.getJsonObj(input, situation) }

        val result : List<Map<String, TriEither<JsonElement,Pair<Int, JsonElement>, Pair<Int,Set<JsonElement>>>>>
            =  MathUtils.generateAllCombinations(
            otoObj,
            mtoObj,
            mtmObj
        )

        return JsonArray(result.map{ outMap ->
            JsonArray(
                outMap.map {
                    JsonObject(
                        mutableMapOf(
                            "cost_name" to Json.encodeToJsonElement(it.key),
                        ).apply {
                            when(val value = it.value){
                                is TriEither.Left -> this["cost_value"] = value.value
                                is TriEither.Middle ->{
                                    this["index"] = Json.encodeToJsonElement(it.key)
                                    this["cost_value"] = value.value.second
                                }
                                is TriEither.Right -> {
                                    this["index"] = Json.encodeToJsonElement(it.key)
                                    this["cost_value"] = JsonArray(value.value.second.toList())
                                }
                            }
                        }
                    )
                }.toList()
            )
        }.toList())
    }

    fun practice(input : Map<String,Any> = mapOf(),situation: ActivationSituation) : Map<String,Any>{
        //这里是要所有的practice的返回值
        val obj = getSelectScope(input, situation)[situation.costIndex]
        val keys : Map<String,Int?> = obj.mapValues {entry ->
            entry.value.fold(
                {null},
                {it.first},
                {it.first}
            )
        }
        return costs.mapValues {
            if (!keys.containsKey(it.key)) return@mapValues Unit
            val k = keys[it.key]
            when(val value = it.value) {
                is OneToOneSelectionCost<*> -> value.practice(input,situation)
                is ManyToOneSelectionCost<*> -> value.practice(input,k?:return@mapValues Unit,situation)
                is ManyToManySelectionCost<*> -> value.practice(input,k?:return@mapValues emptySet<Any>(),situation)
            }
        }
    }
}