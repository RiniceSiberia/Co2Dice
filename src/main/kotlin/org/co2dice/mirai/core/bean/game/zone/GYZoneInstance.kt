package org.co2dice.mirai.core.bean.game.zone

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.GYCardInstance
import org.co2dice.mirai.core.bean.effect.prototype.gy.GYEffect
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-11-20:06
 * @Message: Have a good time!  :)
 **/
class GYZoneInstance(
    override var holder: PlayerInstance) : StackZoneInstance<GYCardInstance, GYEffect>(holder, mutableListOf()){
    override fun constructInstance(card: CardEntry<*>, registry: UniqueIdRegistry): GYCardInstance {
        return GYCardInstance(card,registry,holder)
    }
}