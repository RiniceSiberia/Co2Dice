package org.co2dice.mirai.core.bean.game.zone.instance

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.GYCardInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-11-20:06
 * {@code @Message:} Have a good time!  :)
 **/
class GyZoneInstance(
    override var holder: PlayerInstance) : StackZoneInstance<GYCardInstance>(holder, mutableListOf()){
    override fun constructInstance(card: CardEntry, registry: UniqueIdRegistry): GYCardInstance {
        return GYCardInstance(card,holder)
    }
}