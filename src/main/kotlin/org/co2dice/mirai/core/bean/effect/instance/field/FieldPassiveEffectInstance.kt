package org.co2dice.mirai.core.bean.effect.instance.field

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.bean.card.prototype.Card
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.field.FieldEffect
import org.co2dice.mirai.core.utils.situation.Situation
import org.co2dice.mirai.core.utils.ConstantUtils

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-23-16:43
 * @Message: Have a good time!  :)
 **/
class FieldPassiveEffectInstance(
    entries : List<EffectEntry<FieldEffect>>
) : EffectInstance<FieldEffect>(entries) {

    constructor(entry : EffectEntry<FieldEffect>) : this(listOf(entry))

    constructor(card : Card) : this(
        entries = card.effects.filterIsInstance<FieldEffect>().map { EffectEntry<FieldEffect>(it) }
    )

    fun trigger(situation: Situation) : Int? {
        //循环遍历每个entries里的effect的launchConditions,如果有一个为true,则返回index
        for (i in entries.indices){
            val eff = entries[i].effect
            val level = entries[i].level
            val param = Params(
                mutableMapOf(
                    ConstantUtils.SITUATION_AST_SIGN to situation
                )
            )
            param.add(ConstantUtils.SKILL_LEVEL_SIGN,level)
            if (eff.launchConditions.execute(param) == true){
                return i
            }
        }
        return null
    }



    override fun invoke(index: Int, situation: Situation, input: Map<String, Any>): String? {
        if (trigger(situation) != null){
            val eff = entries[index].effect
            val level = entries[index].level
            val param = Params(
                mutableMapOf(
                    ConstantUtils.SITUATION_AST_SIGN to situation
                )
            )
            input.forEach{param.add(it.key,it.value)}
            param.add(ConstantUtils.SKILL_LEVEL_SIGN,level)
            val c = eff.check.execute(param)
            val r = eff.react.execute(param)
            return if (c != null && r != null){
                param.add(ConstantUtils.CHECK_VALUE_SIGN,c)
                param.add(ConstantUtils.REACT_VALUE_SIGN,r)
                val result = eff.operation.execute(param)
                result ?: "效果无法处理"
            }else{
                "检定骰投掷检定失败"
            }
        }else{
            return null
        }
    }
}