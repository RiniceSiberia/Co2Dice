package org.co2dice.mirai.core.bean.card.instance

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.instance.banish.BanishEffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.banish.BanishEffect
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-27-23:18
 * @Message: Have a good time!  :)
 **/
class BanishCardInstance(
    entry: CardEntry<*>,
    registry : UniqueIdRegistry,
    override var holder: PlayerInstance,
    override var effects: EffectInstance<BanishEffect>
    = BanishEffectInstance( entry.effectEntry.filterIsInstance<EffectEntry<BanishEffect>>()),
) : PublicCardInstance<BanishEffect>(entry,registry), DependPlayer<PlayerInstance> {
}