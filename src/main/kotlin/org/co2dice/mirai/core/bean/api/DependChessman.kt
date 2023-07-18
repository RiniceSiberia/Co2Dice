package org.co2dice.mirai.core.bean.api

import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-02-24-22:46
 * {@code @Message:} Have a good time!  :)
 **/
interface DependChessman {
    var chessman: ChessmanInstance?
    //持有者可为空
}