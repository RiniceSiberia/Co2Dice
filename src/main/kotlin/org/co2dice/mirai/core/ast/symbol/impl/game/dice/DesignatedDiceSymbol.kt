package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.dice.entry.DesignatedDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-19:04
 * @Message: Have a good time!  :)
 **/
object DesignatedDiceSymbol : UniOpSymbol<DesignatedDice, Int>() {

    override fun operation(item: Int, params:Params): DesignatedDice {
        return DesignatedDice(item)
    }

    override fun natualSign(input: INode<out Int>): String {
        return input.natualSerialize()
    }
}