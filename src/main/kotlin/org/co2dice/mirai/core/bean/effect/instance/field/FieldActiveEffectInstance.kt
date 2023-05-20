package org.co2dice.mirai.core.bean.effect.instance.field

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.prototype.Card
import org.co2dice.mirai.core.bean.effect.EffectTargets
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.field.FieldEffect
import org.co2dice.mirai.core.utils.situation.Situation
import org.co2dice.mirai.core.utils.ConstantUtils
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-21-22:47
 * @Message: Have a good time!  :)
 **/
class FieldActiveEffectInstance(
    entries : List<EffectEntry<FieldEffect>>
) : EffectInstance<FieldEffect>(entries) {

    constructor(entry : CardEntry<*>) : this(
        entries = entry.effectEntry.filterIsInstance<EffectEntry<FieldEffect>>()
    )

    override fun preActivationCheck(situation: PreActivationSituation): List<Int> {
        for (effectEntry in entries){
            try {
                val params = effectEntry.effect.paramTypes()
            }catch (e : Exception){
                //出现问题说明param name conflict,报错跳过
                e.printStackTrace()
                continue
            }
        }
    }


    override fun invoke(index : Int, situation: Situation, input: Map<String, Any>): String {
        try {
            val eff = entries[index].effect
            val level = entries[index].level
            val param = Params(
                mutableMapOf(
                    ConstantUtils.SITUATION_AST_SIGN to situation
                )
            )
            input.forEach{param.add(it.key,it.value)}
            param.add(ConstantUtils.SKILL_LEVEL_SIGN,level)
            //准备工作
            return if (eff.launchConditions.execute(param) == true){
                if (eff.inputLegal(input)){
                    targetSelect(param,eff)
                }else{
                    return "输入参数不合法"
                }
            }else{
                "发动条件不成立"
            }
        }catch (e : Exception){
            e.printStackTrace()
            return "异常效果，请联系管理员"
        }
    }


    override fun canLaunch(param : Params, eff : FieldEffect) : Boolean{
        return eff.launchConditions.execute(param) == true
    }


    fun targetSelect(param : Params,eff : FieldEffect) : String{
        val targets : EffectTargets
        return try {
            targets = eff.targetFunction.execute(param)!!
            param.add(ConstantUtils.EFFECT_TARGETS_SIGN,targets)
            payCost(param,eff)
        }catch (e : Exception){
            "目标选择错误"
        }
    }

    fun payCost(param : Params,eff : FieldEffect) : String{
        val cost = eff.cost.execute(param)
        return if (cost != null){
            check(param,eff)
        }else{
            "cost不足"
        }
    }






}