package org.co2dice.mirai.core.bean.effect.triggered_ability.instance

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.bean.api.InstanceStructure
import org.co2dice.mirai.core.bean.api.PermanentInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.TriggeredAbilityEntry
import org.co2dice.mirai.core.utils.situation.PreActivationSituation
import org.co2dice.mirai.core.utils.situation.ResolutionSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-28-22:50
 * @Message: Have a good time!  :)
 **/
sealed class TriggeredAbilityInstance (
    override val entry : TriggeredAbilityEntry,
) : InstanceStructure<TriggeredAbilityEntry>{

    fun invoke(situation: ResolutionSituation) : Boolean{
        return entry.prototype.operation.execute<Boolean>(Params(mutableMapOf(),situation)) ?: false
    }

    fun launchConditions(situation: PreActivationSituation) : Boolean{
        return entry.prototype.launchConditions.execute(Params(situation = situation)) ?: false
    }

    fun targetFunction(situation: PreActivationSituation) : PermanentInstance?{
        return entry.prototype.targetFunction.execute(Params(situation = situation))
    }

}