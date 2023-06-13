package org.co2dice.mirai.core.ast.node

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.symbol.basic.ConstantLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-01-13:35
 * @Message: Have a good time!  :)
 **/
class ConstantLeafNode<O : Any>(override var symbol: ConstantLeafSymbol<O>,
                                val value: O) : INode<O>, ISymbolHolder<ConstantLeafSymbol<O>> {
    override fun evaluate(params:Params): O {
        return symbol.operation(value)
    }

    override fun check(params:Params): O {
        return symbol.check(value)
    }

    override fun serialize(): JsonObject {
//        val json = JsonObject()
//        json.addProperty("symbol", symbol::class.java.simpleName)
//        json.addProperty("value", value.toString())
        return JsonObject(mapOf(
            "symbol" to Json.encodeToJsonElement(symbol::class.java.simpleName),
            "value" to symbol.unwrap(value)
        ))
    }

    override fun natualSerialize(): String {
//        return value.toString() + " : ${symbol.natualSign}"
        return symbol.natualSign(value)
    }

    override fun getChild(): List<INode<*>> {
        return listOf()
    }

    @Deprecated("测试替换节点方法，不知道啥时候会删，谨慎使用", ReplaceWith("false"))
    override fun <T> replaceNode(input: INode<T>, output: INode<T>): Boolean {
        return false
    }
}