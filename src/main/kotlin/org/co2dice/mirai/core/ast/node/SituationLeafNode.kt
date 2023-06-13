package org.co2dice.mirai.core.ast.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.symbol.basic.SituationLeafSymbol
import org.co2dice.mirai.core.utils.situation.SituationApi

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-05-22:29
 * @Message: Have a good time!  :)
 **/
class SituationLeafNode<O : SituationApi>(override var symbol: SituationLeafSymbol<O>
) : INode<O>, ISymbolHolder<SituationLeafSymbol<O>> {

    override fun evaluate(params:Params): O {
        return symbol.operation(params)
    }

    override fun check(params:Params): O {
        return symbol.check(params)
    }

    override fun serialize(): JsonObject {
//        val json = JsonObject()
//        json.addProperty("symbol", symbol::class.java.simpleName)
//        json.addProperty("key", key)
        return JsonObject(mapOf(
            "symbol" to Json.encodeToJsonElement(symbol::class.java.simpleName),
        ))
    }

    override fun natualSerialize(): String {
//        return "param(type :: ${symbol.natualSign},key = $key)"
        return symbol.natualSign()
    }

    override fun getChild(): List<INode<*>> {
        return listOf()
    }

    @Deprecated("测试替换节点方法，不知道啥时候会删，谨慎使用", ReplaceWith("false"))
    override fun <T> replaceNode(input: INode<T>, output: INode<T>): Boolean {
        return false
    }
}