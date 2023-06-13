package org.co2dice.mirai.core.ast.node

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.symbol.basic.ConsumerSymbol
import org.co2dice.mirai.core.ast.tree.AstTree

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-08-0:00
 * @Message: Have a good time!  :)
 **/
class ConsumerNode<O : Any,A : Any>(
    override var symbol: ConsumerSymbol<O, A>,
    val function : AstTree
) : INode<O>, ISymbolHolder<ConsumerSymbol<O, A>> {
    override fun evaluate(params: Params): O {
        return symbol.operation(function,params)
    }

    override fun check(params: Params): O {
        return symbol.check(function,params)
    }

    override fun serialize(): JsonObject {
        return JsonObject(mapOf(
            "symbol" to Json.encodeToJsonElement(symbol::class.java.simpleName),
            "function" to Json.encodeToJsonElement(function.serialize())
        ))
    }

    override fun natualSerialize(): String {
        return symbol.natualSign(function)
    }

    override fun getChild(): List<INode<*>> {
        return emptyList()
    }

    @Deprecated("测试替换节点方法，不知道啥时候会删，谨慎使用")
    override fun <T> replaceNode(input: INode<T>, output: INode<T>): Boolean {
        return false
    }

}