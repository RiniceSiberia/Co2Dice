package org.co2dice.mirai.core.bean.api

import org.co2dice.mirai.core.bean.player.instance.PlayerInstance


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-11-18:11
 * @Message: Have a good time!  :)
 **/
interface DependPlayer <T : PlayerInstance?>{
    var holder: T
    //持有者可为空
}