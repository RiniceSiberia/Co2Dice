package org.co2dice.mirai.core.bean.card.instance.gy

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.api.PublicCardInstance
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-23:45
 * @Message: Have a good time!  :)
 **/
class GYCardInstance (
    entry: CardEntry,
    registry : UniqueIdRegistry,
    override var holder: PlayerInstance,
) : PublicCardInstance<>(entry,registry), DependPlayer<PlayerInstance> {
    val activeEffects: MutableList<EffectEntry> =
}