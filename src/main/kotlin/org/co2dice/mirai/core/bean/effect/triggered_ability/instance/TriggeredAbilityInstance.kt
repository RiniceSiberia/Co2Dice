package org.co2dice.mirai.core.bean.effect.triggered_ability.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.bean.api.InstanceStructure
import org.co2dice.mirai.core.bean.api.PermanentInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.TriggeredAbilityEntry
import org.co2dice.mirai.core.utils.situation.PreActivationSituation
import org.co2dice.mirai.core.utils.situation.ResolutionSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-28-22:50
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable
sealed class TriggeredAbilityInstance () : InstanceStructure<TriggeredAbilityEntry>{

    fun invoke(situation: ResolutionSituation) : Boolean{
        return entry.prototype.operation.execute<Boolean>(Params(mutableMapOf(),situation)) ?: false
    }

    fun launchConditions(situation: PreActivationSituation) : Boolean{
        return entry.prototype.launchConditions.execute(Params(situation = situation)) ?: false
    }

    fun targetFunction(situation: PreActivationSituation) : ?{
        return entry.prototype.target.execute(Params(situation = situation))
    }

}