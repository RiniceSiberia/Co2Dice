package org.co2dice.mirai.ast.symbol.impl.game.dice

import org.co2dice.mirai.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.bean.dice.DiceResult
import org.co2dice.mirai.bean.dice.diceList.DiceList

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-10-18:02
 * @Message: Have a good time!  :)
 **/
object Roll : UniOpSymbol<DiceResult, DiceList>() {
    override val natualSign: String = "roll"

    override fun operation(item: DiceList): DiceResult {
        return item.roll()
    }
}