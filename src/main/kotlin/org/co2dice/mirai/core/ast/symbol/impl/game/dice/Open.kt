package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.dice.DiceResult

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-10-19:39
 * @Message: Have a good time!  :)
 **/
object Open : UniOpSymbol<Int, DiceResult>() {
    init {
        SymbolRegistry.register(this)
    }

    override fun operation(input: DiceResult): Int {
        return input.open()
    }

    override fun natualSign(input: INode<DiceResult>): String {
        return "$input.open()"
    }
}