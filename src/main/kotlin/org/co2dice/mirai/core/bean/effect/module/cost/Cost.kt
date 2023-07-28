package org.co2dice.mirai.core.bean.effect.module.cost

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import org.co2dice.mirai.core.utils.MathUtils
import org.co2dice.mirai.core.utils.MathUtils.listToMap
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

//这个类是cost的判定条件表，用于判定能否支付cost的逻辑
@Serializable
sealed interface Cost {
    fun check(input : Map<String,Any> = mapOf(), situation : PreActivationSituation) : Boolean

}

sealed interface OneToOneSelectionCost<T : Any> : Cost{

    override fun check(input: Map<String, Any>, situation: PreActivationSituation): Boolean {
        return getCostScope(input,situation) != null
    }
    fun getCostScope(input : Map<String,Any> = mapOf(), situation : PreActivationSituation) : T?
    fun practice(input : Map<String,Any> = mapOf(), situation : ActivationSituation) : Any{
        return practice(input,getCostScope(input,situation) ?:return false,situation)
    }

    fun practice(input : Map<String,Any> = mapOf(),obj : T, situation : ActivationSituation) : Any
    fun toJson(obj : T) : JsonElement

    fun getJsonObj(input: Map<String, Any>, situation: PreActivationSituation): JsonElement {
        return toJson(getCostScope(input,situation) ?:return JsonNull)
    }
}


sealed interface ManyToOneSelectionCost<T : Any> : Cost{

    override fun check(input: Map<String, Any>, situation: PreActivationSituation): Boolean {
        return getCostScope(input, situation).isNotEmpty()
    }
    fun getCostScope(input : Map<String,Any> = mapOf(), situation : PreActivationSituation) : Map<Int,T>

    fun practice(input : Map<String,Any> = mapOf(),index : Int, situation : ActivationSituation) : Any{
        return practice(input,getCostScope(input,situation)[index] ?:return false,situation)
    }

    fun practice(input : Map<String,Any> = mapOf(),obj : T, situation : ActivationSituation) : Any
    fun toJson(obj : T) : JsonElement

    fun getJsonObj(input: Map<String, Any>, situation: PreActivationSituation): Map<Int, JsonElement> {
        return getCostScope(input,situation).mapValues { toJson(it.value) }
    }
}

sealed interface ManyToManySelectionCost<T : Any> : Cost{

    fun getScope(input: Map<String, Any>, situation: PreActivationSituation) : Collection<T>

    override fun check(input: Map<String, Any>, situation: PreActivationSituation): Boolean {
        return getCostScope(input,situation).isNotEmpty()
    }

    fun getCostScope(input: Map<String, Any>, situation: PreActivationSituation): Map<Int, Set<T>> {
        return MathUtils.powerSet(getScope(input, situation)).toList().listToMap()
    }
    fun practice(input : Map<String,Any> = mapOf(),index : Int, situation : ActivationSituation) : Set<Any>{
        return practice(input,getCostScope(input,situation)[index] ?:return setOf(),situation)
    }
    fun practice(input : Map<String,Any> = mapOf(),obj : Set<T>, situation : ActivationSituation) : Set<Any>
    fun toJson(obj : T) : JsonElement

    fun getJsonObj(input: Map<String, Any>, situation: PreActivationSituation) : Map<Int, Set<JsonElement>> {
        return getCostScope(input,situation).mapValues { it.value.map {v -> toJson(v) }.toSet() }
    }
}

