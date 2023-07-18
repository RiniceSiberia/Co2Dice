package org.co2dice.mirai.core.bean.api

import org.co2dice.mirai.core.bean.player.instance.PlayerInstance


/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2022-12-11-18:11
 * {@code @Message:} Have a good time!  :)
 **/
interface DependPlayer {
    var holder: PlayerInstance
    //持有者可为空
}