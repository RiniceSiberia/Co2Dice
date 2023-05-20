package org.co2dice.mirai.core.ast.symbol.impl.game.attribute

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.core.bean.attribute.prototype.AttributeAPI
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-22:47
 * @Message: Have a good time!  :)
 **/
object GetAttributeValue : BiOpSymbol<Int, ChessmanInstance, AttributeAPI>() {
    init {
        SymbolRegistry.register(this)
    }

    override fun operation(l: ChessmanInstance, r: AttributeAPI): Int {
        return l.attributeTable.getValue(r)!!
    }


    override fun natualSign(left: INode<ChessmanInstance>, right: INode<AttributeAPI>): String {
        return "${left.natualSerialize()}.getAttributeValue(${right.natualSerialize()})"
    }

}