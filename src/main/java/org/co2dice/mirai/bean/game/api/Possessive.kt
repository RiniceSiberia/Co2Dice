package org.co2dice.mirai.bean.game.api

import org.co2dice.mirai.bean.game.prototype.character.Chess


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-11-18:11
 * @Message: Have a good time!  :)
 **/
interface Possessive {
    var holder: Chess?
    //持有者可为空
}