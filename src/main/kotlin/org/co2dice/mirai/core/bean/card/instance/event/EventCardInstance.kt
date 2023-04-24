package org.co2dice.mirai.core.bean.card.instance.event

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.api.PublicCardInstance
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:18
 * @Message: Have a good time!  :)
 **/
class EventCardInstance(
    entry: CardEntry,
    registry : UniqueIdRegistry,
    override var holder: PlayerInstance? = null,
    var activeEffects: MutableList<EffectEntry>
    = entry.card.effects.map { EffectEntry(registry = registry, effect = it) }.toMutableList(),
    ) : PublicCardInstance<>(entry,registry), DependPlayer<PlayerInstance?>  {

}