package org.co2dice.mirai.core.bean.effect.instance

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.prototype.Effect
import org.co2dice.mirai.core.utils.ConstantUtils
import org.co2dice.mirai.core.utils.situation.PreActivationSituation
import org.co2dice.mirai.core.utils.situation.SituationApi

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-19-20:26
 * @Message: Have a good time!  :)
 **/
abstract class EffectInstance<E :Effect>(
    val entries : List<EffectEntry<E>>
) {

    abstract fun preActivationCheck(situation: PreActivationSituation) : List<Int>
    //返回的是能触发几号效果
    abstract fun invoke (index : Int, situation : SituationApi, input : Map<String,Any>) : String?

    open fun canLaunch(param :Params,eff : E) : Boolean{
        return eff.launchConditions.execute(param) == true
    }

    open fun operation(param :Params,eff : E) : String{
        val result = eff.operation.execute(param)
        return result ?: "失去对象,效果不处理"
    }
    open fun check(param :Params,eff : E) : String{
        val c = eff.check.execute(param)
        val r = eff.react.execute(param)
        return if (c != null && r != null){
            param.add(ConstantUtils.CHECK_VALUE_SIGN,c)
            param.add(ConstantUtils.REACT_VALUE_SIGN,r)
            operation(param,eff)
        }else{
            "检定骰投掷检定失败"
        }
    }
}