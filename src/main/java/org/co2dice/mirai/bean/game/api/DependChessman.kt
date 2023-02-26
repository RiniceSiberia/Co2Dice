package org.co2dice.mirai.bean.game.api

import org.co2dice.mirai.bean.game.prototype.character.Chessman

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-02-24-22:46
 * @Message: Have a good time!  :)
 **/
interface DependChessman {
    var chessman: Chessman?
    //持有者可为空
}