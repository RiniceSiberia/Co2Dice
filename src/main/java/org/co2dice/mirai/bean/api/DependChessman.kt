package org.co2dice.mirai.bean.api

import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-02-24-22:46
 * @Message: Have a good time!  :)
 **/
interface DependChessman {
    var chessman: ChessmanInstance?
    //持有者可为空
}