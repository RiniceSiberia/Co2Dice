package org.co2dice.mirai.core.bean.card.instance.unpublic

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.release.UnPublicEffect
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-14-20:26
 * @Message: Have a good time!  :)
 **/
class UnPublicCardInstance (
    entry: CardEntry,
    registry : UniqueIdRegistry,
    override var holder: PlayerInstance,
    override var effects: EffectInstance<UnPublicEffect>,
) : CardInstance<UnPublicEffect>(entry,registry), DependPlayer<PlayerInstance> {
    var open = false
}
