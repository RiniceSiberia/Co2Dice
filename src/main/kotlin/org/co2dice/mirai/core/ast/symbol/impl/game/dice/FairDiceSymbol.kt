package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.dice.entry.FairDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:59
 * @Message: Have a good time!  :)
 **/
object FairDiceSymbol : UniOpSymbol<FairDice,Int>() {

    override fun operation(item: Int, params:Params): FairDice {
        return FairDice(item)
    }

    override fun natualSign(input: INode<Int>): String {
        return "D${input.natualSerialize()}"
    }
}