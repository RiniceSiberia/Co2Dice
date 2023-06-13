package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.dice.entry.IntSampleSpace

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-10-18:02
 * @Message: Have a good time!  :)
 **/
object DiceRollSymbol : UniOpSymbol<Int, IntSampleSpace>() {
    override fun operation(input: IntSampleSpace, params:Params): Int {
        return input.roll()
    }

    override fun natualSign(input: INode<IntSampleSpace>): String {
        return "$input.roll()"
    }
}