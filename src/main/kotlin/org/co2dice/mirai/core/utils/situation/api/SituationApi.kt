package org.co2dice.mirai.core.utils.situation.api

import org.co2dice.mirai.core.bean.game.Scene
import org.co2dice.mirai.core.bean.game.zone.ZoneInstanceSet
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-11-22:21
 * @Message: Have a good time!  :)
 **/
interface SituationApi {
    val input : Map<String,Any>
    val scene: Scene
    val player: PlayerInstance

    fun getActionHolderZone() : ZoneInstanceSet?{
        return scene.zones[player]
    }
}