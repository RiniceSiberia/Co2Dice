package org.co2dice.mirai.core.bean.effect.module.cost

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

//这个类是cost的判定条件表，用于判定能否支付cost的逻辑
@Serializable
sealed interface Cost<C,O : Any> {
    val costName : String
    fun getCosts(situation : PreActivationSituation) : C

    fun practice(obj : O, situation : ActivationSituation) : Boolean

}

sealed interface OnlySelectionCost<T : Any> : Cost<T?,T>{
    fun operate(situation : ActivationSituation) : Boolean{
        val obj = getCosts(situation)
        if (obj != null){
            return practice(obj, situation)
        }
        return false
    }
}

sealed interface MultipleSelectionCost<T : Any> : Cost<List<T>,T>{
    fun operate(situation : ActivationSituation) : Boolean{
        val list = getCosts(situation)
        val index = situation.costIndex[costName]
        if (index != null && index in list.indices){
            return practice(list[index],situation)
        }
        return false
    }
}
