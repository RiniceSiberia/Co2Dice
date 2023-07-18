package org.co2dice.mirai.core.ast.symbol.impl.game.attribute

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.core.bean.attribute.prototype.Attribute

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-03-0:06
 * {@code @Message:} Have a good time!  :)
 **/
object ToAttributeMapEntry : BiOpSymbol<Pair<Attribute, Int>,Attribute,Int>() {

    override fun natualSign(left: INode<out Attribute>, right: INode<out Int>): String {
        return "Map.EntryStructure<Attribute,Intelligence>(${left.natualSerialize()},${right.natualSerialize()})"
    }

    override fun operation(l: Attribute, r: Int, params: Params): Pair<Attribute, Int> {
        return Pair(l,r)
    }
}