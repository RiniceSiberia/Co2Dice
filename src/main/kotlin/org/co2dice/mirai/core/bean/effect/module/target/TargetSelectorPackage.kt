package org.co2dice.mirai.core.bean.effect.module.target

import kotlinx.serialization.json.*
import org.co2dice.mirai.core.utils.MathUtils
import org.co2dice.mirai.core.utils.TriEither
import org.co2dice.mirai.core.utils.filterAsInstance
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-18-22:37
 * @Message: 打包的目标选择器
 **/
interface TargetSelectorPackage {

    val selectors : Map<String,TargetSelector>

    fun check(input : Map<String,Any>,situation: PreActivationSituation): Boolean{
        return getSelectScope(input, situation).isNotEmpty()
    }

    fun getSelectScope(input : Map<String,Any>,situation: PreActivationSituation)
    : List<Map<String, TriEither<Any,Pair<Int,Any>,Pair<Int,Set<Any>>>>>{
        //返回值根据不同情况分三种any:一对一，map<int,any>:多对一，map<int,set<any>>:多对多
        //首先,将selectors的类型分为三类:一对一，多对一，多对多
        //一对一的不用管，不需要遍历，多对一的当作一个list即可，多对多的当作一个list<set>,将三者分别列出

        if (selectors.any { !it.value.check(input, situation) }) return listOf()

        val oneToOne : List<Pair<String,OneToOneTargetSelector<*>>>
        = selectors.map { it.toPair() }.toList().filterAsInstance()
        val manyToOne : List<Pair<String,ManyToOneTargetSelector<*>>>
        = selectors.map { it.toPair() }.toList().filterAsInstance()
        val manyToMany : List<Pair<String,ManyToManyTargetSelector<*>>>
        = selectors.map { it.toPair() }.toList().filterAsInstance()


        //先将一对一的转为map<String,Any>
        val otoObj : Map<String,Any>
        = oneToOne.associate { it.first to it.second.getSelectScope(input, situation)!! }
        //再将多对一的转为map<String,map<int,any>>
        val mtoObj : Map<String,Map<Int,Any>>
        = manyToOne.associate { it.first to it.second.getSelectScope(input, situation) }
        //再将多对多的转为map<String,map<int,set<any>>>
        val mtmObj : Map<String,Map<Int,Set<Any>>>
        = manyToMany.associate { it.first to it.second.getSelectScope(input, situation) }

        return MathUtils.generateAllCombinations(
            otoObj,
            mtoObj,
            mtmObj
        )
    }

    fun getSelectScopeJson(input : Map<String,Any>,situation: PreActivationSituation): JsonElement{
        //返回值根据不同情况分三种any:一对一，map<int,any>:多对一，map<int,set<any>>:多对多
        //首先,将selectors的类型分为三类:一对一，多对一，多对多
        //一对一的不用管，不需要遍历，多对一的当作一个list即可，多对多的当作一个list<set>,将三者分别列出

        if (selectors.any { !it.value.check(input, situation) }) return JsonNull

        val oneToOne : List<Pair<String,OneToOneTargetSelector<*>>>
            = selectors.map { it.toPair() }.toList().filterAsInstance()
        val manyToOne : List<Pair<String,ManyToOneTargetSelector<*>>>
            = selectors.map { it.toPair() }.toList().filterAsInstance()
        val manyToMany : List<Pair<String,ManyToManyTargetSelector<*>>>
            = selectors.map { it.toPair() }.toList().filterAsInstance()

        //先将一对一的转为map<String,Any>
        val otoObj : Map<String,JsonElement>
            = oneToOne.associate { it.first to it.second.getJsonObj(input,situation).jsonObject }
        //再将多对一的转为map<String,map<int,any>>
        val mtoObj : Map<String,Map<Int,JsonElement>>
            = manyToOne.associate { it.first to it.second.getJsonObj(input, situation) }
        //再将多对多的转为map<String,map<int,set<any>>>
        val mtmObj : Map<String,Map<Int,Set<JsonElement>>>
            = manyToMany.associate { it.first to it.second.getJsonObj(input, situation) }

        val result : List<Map<String, TriEither<JsonElement,Pair<Int,JsonElement>, Pair<Int,Set<JsonElement>>>>>
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
                            "target_name" to Json.encodeToJsonElement(it.key),
                        ).apply {
                            when(val value = it.value){
                                is TriEither.Left -> this["target_value"] = value.value
                                is TriEither.Middle ->{
                                    this["index"] = Json.encodeToJsonElement(it.key)
                                    this["target_value"] = value.value.second
                                }
                                is TriEither.Right -> {
                                    this["index"] = Json.encodeToJsonElement(it.key)
                                    this["target_value"] = JsonArray(value.value.second.toList())
                                }
                            }
                        }
                    )
                }.toList()
            )
        }.toList())
    }

    fun practice(input : Map<String,Any>,situation: ActivationSituation) : Map<String,Any>?{
//        Map<String, TriEither<Any,Pair<Int,Any>,Pair<Int,Set<Any>>>>
        if (selectors.any { !it.value.check(input,situation) }){
            return null
        }
        return getSelectScope(input,situation)[situation.targetIndex].mapValues { it.value.get() }
    }
}