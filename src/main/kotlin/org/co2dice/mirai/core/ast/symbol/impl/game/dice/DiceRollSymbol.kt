package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.dice.entry.DispersedSpace

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-10-18:02
 * {@code @Message:} Have a good time!  :)
 **/
object DiceRollSymbol : UniOpSymbol<Int, DispersedSpace<Int>>() {
    override fun operation(input: DispersedSpace<Int>, params:Params): Int {
        return input.roll()
    }

    override fun natualSign(input: INode<out DispersedSpace<Int>>): String {
        return "$input.roll()"
    }
}