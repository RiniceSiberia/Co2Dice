package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.dice.roll

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-10-18:02
 * {@code @Message:} Have a good time!  :)
 **/
object DiceRollSymbol : UniOpSymbol<Int, HashMap<Int,Int>>() {
    override fun operation(input: HashMap<Int,Int>, params:Params): Int {
        return input.roll()
    }

    override fun natualSign(input: INode<out HashMap<Int,Int>>): String {
        return "$input.roll()"
    }
}