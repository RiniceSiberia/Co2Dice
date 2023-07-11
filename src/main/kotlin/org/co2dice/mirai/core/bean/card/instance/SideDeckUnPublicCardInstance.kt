package org.co2dice.mirai.core.bean.card.instance

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.api.agent.SpellAgent
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.EnterFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-30-12:59
 * @Message: 事件卡和场地卡的类(但是未公开)
 **/
class SideDeckUnPublicCardInstance (
    entry: CardEntry,
    override var holder: PlayerInstance,
    override val spellAbilities: List<EnterFieldTriggeredAbilityInstance>,
) : CardInstance(entry), SpellAgent, DependPlayer{
}