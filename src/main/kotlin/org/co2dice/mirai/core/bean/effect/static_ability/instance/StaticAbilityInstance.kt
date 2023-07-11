package org.co2dice.mirai.core.bean.effect.static_ability.instance

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.api.InstanceStructure
import org.co2dice.mirai.core.bean.effect.static_ability.entry.StaticAbilityEntry
import org.co2dice.mirai.core.utils.situation.SituationApi

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-23-18:43
 * @Message: Have a good time!  :)
 **/
sealed class StaticAbilityInstance(
    override val entry : StaticAbilityEntry,
    val trigger : AstTree  = entry.prototype.trigger,
    val operation : AstTree = entry.prototype.operation,
) : InstanceStructure<StaticAbilityEntry>{

    fun trigger(situationApi: SituationApi) : Boolean{
        return trigger.execute<Boolean>(Params(situation = situationApi)) ?: false
    }

    fun operation(situationApi: SituationApi) : Boolean{
        return operation.execute<Boolean>(Params(situation = situationApi)) ?: false
    }
}