package org.co2dice.mirai.ast.symbol.impl.game.dice

import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.bean.dice.single.ConstantDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-19:04
 * @Message: Have a good time!  :)
 **/
object ConstantDiceSymbol : UniOpSymbol<ConstantDice, Int>() {
    init {
        SymbolRegistry.register(this)
    }

    override val natualSign: String = "ConstantDiceSymbol"
    override fun operation(item: Int): ConstantDice {
        return ConstantDice(item)
    }
}