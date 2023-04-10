package org.co2dice.mirai.ast.symbol.impl.game.dice

import org.co2dice.mirai.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.bean.dice.DiceResult

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-10-19:39
 * @Message: Have a good time!  :)
 **/
object Open : UniOpSymbol<Int, DiceResult>() {
    override val natualSign: String = "open"

    override fun operation(item: DiceResult): Int {
        return item.open()
    }
}