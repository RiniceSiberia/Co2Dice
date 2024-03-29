package org.co2dice.mirai.core.bean.effect.triggered_ability.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.TriggeredAbilityEntry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-07-23:31
 * {@code @Message:} 骰子揭示实例
 **/
@Serializable
class DiceRevealTriggeredAbilityInstance(
    override val entry : TriggeredAbilityEntry
) : TriggeredAbilityInstance() {
}