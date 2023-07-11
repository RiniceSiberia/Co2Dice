package org.co2dice.mirai.core.ast.node

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-01-13:34
 * @Message: Have a good time!  :)
 **/
class UniOpNode<O : Any,I : Any>(
    override var symbol: UniOpSymbol<O, I>,
    var child: INode<out I>
    ) : INode<O>, ISymbolHolder<UniOpSymbol<O, I>> {
    override fun evaluate(params:Params): O {
        return symbol.operation(child.evaluate(params),params)
    }

    override fun check(params:Params): O {
        return symbol.check(child.check(params),params)
    }

    override fun serialize(): JsonObject {
//        val json = JsonObject()
//        json.addProperty("symbol", symbol::class.java.simpleName)
//        json.add("child", child.serialize())
        return JsonObject(mapOf(
            "symbol" to Json.encodeToJsonElement(symbol::class.java.simpleName),
            "child" to Json.encodeToJsonElement(child.serialize())
        ))
    }

    override fun natualSerialize(): String {
//        return "(${symbol.natualSign} ${child.natualSerialize()})"
        return symbol.natualSign(child)
    }

    override fun getChild(): List<INode<*>> {
        return listOf(child)
    }

    @Deprecated("测试替换节点方法，不知道啥时候会删，谨慎使用")
    @Suppress("UNCHECKED_CAST")
    override fun <T> replaceNode(input: INode<T>, output: INode<T>): Boolean {
        if (child == input) {
            child = output as INode<I>
            return true
        }
        return false
    }

}