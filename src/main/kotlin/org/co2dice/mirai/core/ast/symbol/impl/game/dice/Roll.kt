package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.dice.DiceResult
import org.co2dice.mirai.core.bean.dice.diceList.DiceList

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-10-18:02
 * @Message: Have a good time!  :)
 **/
object Roll : UniOpSymbol<DiceResult, DiceList>() {
    init {
        SymbolRegistry.register(this)
    }

    override fun operation(input: DiceList): DiceResult {
        return input.roll()
    }

    override fun natualSign(input: INode<DiceList>): String {
        return "$input.roll()"
    }
}