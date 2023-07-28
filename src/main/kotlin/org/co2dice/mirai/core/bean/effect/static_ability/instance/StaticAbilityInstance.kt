package org.co2dice.mirai.core.bean.effect.static_ability.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.api.InstanceStructure
import org.co2dice.mirai.core.bean.effect.static_ability.entry.StaticAbilityEntry
import org.co2dice.mirai.core.utils.situation.SituationApi

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-23-18:43
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable
sealed class StaticAbilityInstance() : InstanceStructure<StaticAbilityEntry>{
    val trigger : AstTree  = entry.prototype.trigger
    val operation : AstTree = entry.prototype.operation

    fun trigger(situationApi: SituationApi) : Boolean{
        return trigger.execute<Boolean>(Params(situation = situationApi)) ?: false
    }

    fun operation(situationApi: SituationApi) : Boolean{
        return operation.execute<Boolean>(Params(situation = situationApi)) ?: false
    }
}