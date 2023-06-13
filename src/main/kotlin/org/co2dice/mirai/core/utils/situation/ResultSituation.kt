package org.co2dice.mirai.core.utils.situation

import org.co2dice.mirai.core.bean.game.Scene
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-12-12:51
 * @Message: Have a good time!  :)
 **/
data class ResultSituation(
    override val scene: Scene,
    override val player: PlayerInstance

) : SituationApi {
}