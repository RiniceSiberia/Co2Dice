package org.co2dice.mirai.core.utils.situation

import org.co2dice.mirai.core.bean.game.Scene
import org.co2dice.mirai.core.bean.game.zone.*
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-26-20:33
 * @Message: Have a good time!  :)
 **/
interface SituationApi {
    val scene: Scene
    val player: PlayerInstance

    fun getGY() :GYZoneInstance {
        return scene.zones[player]!!.gy
    }

    fun getBanish() : BanishZoneInstance {
        return scene.zones[player]!!.banish
    }

    fun getDeck() :DeckInstance {
        return scene.zones[player]!!.deck
    }

    fun getVenue() :VenueMap {
        return scene.venueMap
    }

    fun getHand() :HandInstance {
        return scene.zones[player]!!.hand
    }

    fun getRegistry() : UniqueIdRegistry {
        return scene.registry
    }
}