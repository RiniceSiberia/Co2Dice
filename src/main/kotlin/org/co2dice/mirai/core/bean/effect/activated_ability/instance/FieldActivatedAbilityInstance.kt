package org.co2dice.mirai.core.bean.effect.activated_ability.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.ActivatedAbilityEntry
import org.co2dice.mirai.core.utils.situation.PreActivationSituation


/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-21-22:47
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable
class FieldActivatedAbilityInstance(
    override val entry : ActivatedAbilityEntry,
) : ActivatedAbilityInstance(){

    fun launchCheck(situation : PreActivationSituation) : Boolean{
        //效果能否发动的检查
        val params = Params(situation = situation)
        return entry.prototype.launchConditions.execute<Boolean>(params) ?:false
    }

    fun costCheck(situation: PreActivationSituation) : Boolean{
        val params = Params(situation = situation)
        return entry.prototype.cost.
    }
}