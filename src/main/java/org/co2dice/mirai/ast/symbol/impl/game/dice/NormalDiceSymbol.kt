package org.co2dice.mirai.ast.symbol.impl.game.dice

import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.bean.dice.single.NormalDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:59
 * @Message: Have a good time!  :)
 **/
object NormalDiceSymbol : UniOpSymbol<NormalDice,Int>() {
    init {
        SymbolRegistry.register(this)
    }

    override val natualSign: String = "NormalDiceSymbol"
    override fun operation(item: Int): NormalDice {
        return NormalDice(item)
    }
}