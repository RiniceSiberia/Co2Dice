package org.co2dice.mirai.core.bean.game.zone

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.BanishCardInstance
import org.co2dice.mirai.core.bean.effect.prototype.banish.BanishEffect
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-11-20:13
 * @Message: Have a good time!  :)
 **/
class BanishZoneInstance(override var holder: PlayerInstance)
    : StackZoneInstance<BanishCardInstance, BanishEffect>(holder, mutableListOf()
    ) {
    override fun constructInstance(card: CardEntry<*>, registry: UniqueIdRegistry): BanishCardInstance {
        return BanishCardInstance(card,registry,holder)
    }
}