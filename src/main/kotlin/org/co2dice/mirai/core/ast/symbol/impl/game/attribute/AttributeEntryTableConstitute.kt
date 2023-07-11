package org.co2dice.mirai.core.ast.symbol.impl.game.attribute

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.ListOpSymbol
import org.co2dice.mirai.core.bean.attribute.prototype.Attribute
import org.co2dice.mirai.core.bean.attribute.table.AttributeTable

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-03-0:04
 * @Message: Have a good time!  :)
 **/
object AttributeEntryTableConstitute : ListOpSymbol<AttributeTable, Pair<Attribute, Int>>() {
    override fun natualSign(list: List<INode<out Pair<Attribute, Int>>>): String {
        return "AttributeEntryTableConstitute(${list.joinToString(",") { it.natualSerialize() }})"
    }

    override fun operation(list: List<Pair<Attribute, Int>>, params: Params): AttributeTable {
        return AttributeTable(list.toMap())
    }
}