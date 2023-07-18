package org.co2dice.mirai.core.ast.node

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.symbol.basic.ListOpSymbol

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-01-13:36
 * {@code @Message:} Have a good time!  :)
 **/
class ListOpNode<O : Any,E : Any>(
    override var symbol: ListOpSymbol<O, E>,
    val children: MutableList<INode<out E>>
    ) : INode<O>, ISymbolHolder<ListOpSymbol<O, E>> {
    override fun evaluate(params:Params): O {
        return symbol.operation(
            list = children.map { it.evaluate(params) },params
        )
    }

    override fun check(params:Params): O {
        return symbol.check(
            list = children.map { it.check(params) },params
        )
    }

    override fun serialize(): JsonObject {
//        val json = JsonObject()
//        json.addProperty("symbol", symbol::class.java.simpleName)
//        json.add("children", JsonArray().apply {
//            children.forEach {
//                add(it.serialize())
//            }
//        })
        return JsonObject(mapOf(
            "symbol" to Json.encodeToJsonElement(symbol::class.java.simpleName),
            "children" to JsonArray(children.map { it.serialize() })
        ))
    }

    override fun natualSerialize(): String {
//        return "(${symbol.natualSign} [${children.joinToString(",") { it.natualSerialize() }}])"
        return symbol.natualSign(children)
    }

    override fun getChild(): List<INode<*>> {
        return children
    }

    @Deprecated("测试替换节点方法，不知道啥时候会删，谨慎使用")
    @Suppress("UNCHECKED_CAST")
    override fun <T> replaceNode(input: INode<T>, output: INode<T>): Boolean {
        children.forEachIndexed { index, node ->
            if (node == input) {
                children[index] = output as INode<out E>
                return true
            }
        }
        return false
    }

}