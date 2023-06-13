package org.co2dice.mirai.core.bean.card.instance

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.instance.release.ReleaseEffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.release.ReleaseEffect
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-14-20:26
 * @Message: Have a good time!  :)
 **/
class UnPublicCardInstance (
    entry: CardEntry<*>,
    registry : UniqueIdRegistry,
    override var holder: PlayerInstance,
    override var effects: EffectInstance<ReleaseEffect> = ReleaseEffectInstance( entry.effectEntry.filterIsInstance<EffectEntry<ReleaseEffect>>()),
) : CardInstance<ReleaseEffect>(entry,registry), DependPlayer<PlayerInstance> {
    var open = false
}
