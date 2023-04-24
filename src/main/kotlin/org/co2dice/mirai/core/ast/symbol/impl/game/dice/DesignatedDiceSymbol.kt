package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.dice.single.DesignatedDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-19:04
 * @Message: Have a good time!  :)
 **/
object DesignatedDiceSymbol : UniOpSymbol<DesignatedDice, Int>() {
    init {
        SymbolRegistry.register(this)
    }

    override fun operation(item: Int): DesignatedDice {
        return DesignatedDice(item)
    }

    override fun natualSign(input: INode<Int>): String {
        return input.natualSerialize()
    }
}