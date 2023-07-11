package org.co2dice.mirai.core.bean.effect.module.cost

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

//这个类是cost的判定条件表，用于判定能否支付cost的逻辑
@Serializable
sealed interface Cost<C,O : Any> {
    fun check(situation : PreActivationSituation) : C

    fun execute(obj : O,situation : ActivationSituation) : Boolean

}

sealed interface OnlySelectionCost<T : Any> : Cost<T?,T>{
    fun operate(situation : ActivationSituation) : Boolean{
        val obj = check(situation)
        if (obj != null){
            return execute(obj, situation)
        }
        return false
    }
}

sealed interface MultipleSelectionCost<T : Any> : Cost<List<T>,T>{
    fun operate(index : Int,situation : ActivationSituation) : Boolean{
        val list = check(situation)
        if (index in list.indices){
            return execute(list[index],situation)
        }
        return false
    }
}
