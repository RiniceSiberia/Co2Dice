package org.co2dice.mirai.core.ast.symbol.impl.game.attribute

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.core.bean.attribute.table.AttributeTable
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-03-0:01
 * @Message: Have a good time!  :)
 **/
object PayAttributeCostSymbol  : BiOpSymbol<Boolean, ChessmanInstance, AttributeTable>() {

    override fun natualSign(left: INode<out ChessmanInstance>, right: INode<out AttributeTable>): String {
        return "${left.natualSerialize()}.anyCheemanAttributeCost(${right.natualSerialize()})"
    }

    override fun operation(l: ChessmanInstance, r: AttributeTable, params: Params): Boolean {
        return l.payAttributeCost(r)
    }

}