package org.co2dice.mirai.core.ast.node

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-05-22:29
 * @Message: Have a good time!  :)
 **/
class ParamLeafNode<O : Any>(override var symbol: ParamLeafSymbol<O>,
                             var key: String) : INode<O>, ISymbolHolder<ParamLeafSymbol<O>> {

    override fun evaluate(params:Params): O {
        return symbol.operation(key, params)
    }

    override fun check(params:Params): O {
        return symbol.check(key,params)
    }

    override fun serialize(): JsonObject {
//        val json = JsonObject()
//        json.addProperty("symbol", symbol::class.java.simpleName)
//        json.addProperty("key", key)
        return JsonObject(mapOf(
            "symbol" to Json.encodeToJsonElement(symbol::class.java.simpleName),
            "key" to Json.encodeToJsonElement(key)
        ))
    }

    override fun natualSerialize(): String {
//        return "param(type :: ${symbol.natualSign},key = $key)"
        return symbol.natualSign(key)
    }

    override fun getChild(): List<INode<*>> {
        return listOf()
    }

    @Deprecated("测试替换节点方法，不知道啥时候会删，谨慎使用", ReplaceWith("false"))
    override fun <T> replaceNode(input: INode<T>, output: INode<T>): Boolean {
        return false
    }
}