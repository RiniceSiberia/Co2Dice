package org.co2dice.mirai.ast.node

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.co2dice.mirai.ast.Params
import org.co2dice.mirai.ast.node.api.ISymbolHolder
import org.co2dice.mirai.ast.node.basic.INode
import org.co2dice.mirai.ast.symbol.basic.ListOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-01-13:36
 * @Message: Have a good time!  :)
 **/
class ListOpNode<O,E>(
    override val symbol: ListOpSymbol<O, E>,
    val children: List<INode<out E>>
    ) : INode<O>, ISymbolHolder<ListOpSymbol<O, out E>> {
    override fun evaluate(params: Params): O {
        return symbol.operation(children.map { it.evaluate(params) })
    }

    override fun serialize(): JsonObject {
        val json = JsonObject()
        json.addProperty("symbol", symbol::class.java.name)
        json.add("children", JsonArray().apply {
            children.forEach {
                add(it.serialize())
            }
        })
        return json
    }

    override fun natualSerialize(): String {
        return "(" +symbol.natualSign + " [" + children.joinToString(",") { it.natualSerialize() } + "])"
    }

}