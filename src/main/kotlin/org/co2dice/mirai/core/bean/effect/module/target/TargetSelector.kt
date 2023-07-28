package org.co2dice.mirai.core.bean.effect.module.target

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import org.co2dice.mirai.core.utils.MathUtils
import org.co2dice.mirai.core.utils.MathUtils.listToMap
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-09-16:47
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable
sealed interface TargetSelector{
    fun check(input : Map<String,Any>,situation : PreActivationSituation) : Boolean
}

sealed interface OneToOneTargetSelector<T : Any> : TargetSelector {
    //不知道啥时候用姑且写进去了
    override fun check(input: Map<String, Any>, situation: PreActivationSituation): Boolean {
        return getSelectScope(input,situation) != null
    }

    fun getSelectScope(input : Map<String,Any>,situation : PreActivationSituation) : T?
    fun toJson(obj : T) : JsonElement
    fun getJsonObj(input: Map<String, Any>, situation: PreActivationSituation): JsonElement {
        return toJson(getSelectScope(input,situation) ?:return JsonNull)
    }
}

sealed interface ManyToOneTargetSelector<T : Any> : TargetSelector {
    override fun check(input: Map<String, Any>, situation: PreActivationSituation): Boolean {
        return getSelectScope(input,situation).isNotEmpty()
    }

    fun getSelectScope(input : Map<String,Any>,situation : PreActivationSituation) : Map<Int,T>
    fun toJson(obj : T) : JsonElement
    fun getJsonObj(input: Map<String, Any>, situation: PreActivationSituation): Map<Int, JsonElement> {
        return getSelectScope(input,situation).mapValues { toJson(it.value) }
    }
}

sealed interface ManyToManyTargetSelector<T : Any> : TargetSelector {
    override fun check(input: Map<String, Any>, situation: PreActivationSituation): Boolean {
        return getSelectScope(input,situation).isNotEmpty()
    }

    fun getScope(input : Map<String,Any>,situation : PreActivationSituation) : Set<T>
    fun toJson(obj : T) : JsonElement

    fun getSelectScope(input: Map<String, Any>, situation: PreActivationSituation): Map<Int,Set<T>> {
        return MathUtils.powerSet(getScope(input,situation)).toList().listToMap()
    }

    fun getJsonObj(input: Map<String, Any>, situation: PreActivationSituation) : Map<Int, Set<JsonElement>> {
        return getSelectScope(input,situation).mapValues { it.value.map {v -> toJson(v) }.toSet() }
    }
}

