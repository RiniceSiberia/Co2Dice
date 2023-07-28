package org.co2dice.mirai.core.bean.card.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.api.agent.SpellAgent
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.EnterFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-30-12:59
 * {@code @Message:} 事件卡和场地卡的类(但是未公开)
 **/
@Serializable
class SideDeckCardInstance (
    override val entry: CardEntry,
    override var holder: PlayerInstance,
    override val spellAbilities: List<EnterFieldTriggeredAbilityInstance> = entry.triggeredAbilityEntries.toInstance(),
) : CardInstance(), SpellAgent, DependPlayer{
}