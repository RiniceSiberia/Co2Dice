package org.co2dice.mirai.core.bean.effect.triggered_ability.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.TriggeredAbilityEntry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-28-22:56
 * {@code @Message:} 战吼实例
 **/
@Serializable
class EnterFieldTriggeredAbilityInstance(
    override val entry : TriggeredAbilityEntry
) : TriggeredAbilityInstance(){
}