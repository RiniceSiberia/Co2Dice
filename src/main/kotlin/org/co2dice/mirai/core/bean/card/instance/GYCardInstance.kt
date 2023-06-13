package org.co2dice.mirai.core.bean.card.instance

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.instance.gy.GYEffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.gy.GYEffect
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-23:45
 * @Message: 墓地发动的效果
 **/
class GYCardInstance (
    entry: CardEntry<*>,
    registry : UniqueIdRegistry,
    override var holder: PlayerInstance,
    override var effects: EffectInstance<GYEffect>
    = GYEffectInstance( entry.effectEntry.filterIsInstance<EffectEntry<GYEffect>>()),
) : PublicCardInstance<GYEffect>(entry,registry), DependPlayer<PlayerInstance> {
}