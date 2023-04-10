package org.co2dice.mirai.ast.symbol.impl.game.dice

import org.co2dice.mirai.ast.symbol.basic.ListOpSymbol
import org.co2dice.mirai.bean.dice.single.AbstractDice
import org.co2dice.mirai.bean.dice.diceList.DiceList as Dices

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-10-18:00
 * @Message: Have a good time!  :)
 **/
object DiceListSymbol : ListOpSymbol<Dices, AbstractDice>() {
    override val natualSign: String = "dice_list"

    override fun operation(list: List<AbstractDice>): Dices {
        return Dices(list)
    }
}