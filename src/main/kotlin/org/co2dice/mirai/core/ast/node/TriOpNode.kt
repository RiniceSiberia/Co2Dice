package org.co2dice.mirai.core.ast.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.symbol.basic.TriOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-24-23:02
 * @Message: Have a good time!  :)
 **/
class TriOpNode<O : Any,F : Any,S : Any,T : Any>(
    override var symbol: TriOpSymbol<O,F,S,T>,
    var first: INode<F>,
    var second: INode<S>,
    var third: INode<T>
) : INode<O>, ISymbolHolder<TriOpSymbol<O,F,S,T>> {
    override fun evaluate(params:Params): O {
        val f = first.evaluate(params)
        val s = second.evaluate(params)
        val t = third.evaluate(params)
        return symbol.operation(f,s,t,params)
    }

    override fun check(params:Params): O {
        return symbol.check(first.check(params),second.check(params),third.check(params),params)
    }

    override fun serialize(): JsonObject {
//        val json = JsonObject()
//        json.addProperty("symbol", symbol::class.java.simpleName)
//        json.add("first", first.serialize())
//        json.add("second", second.serialize())
//        json.add("third", third.serialize())
        return JsonObject(mapOf(
            "symbol" to Json.encodeToJsonElement(symbol::class.java.simpleName),
            "first" to Json.encodeToJsonElement(first.serialize()),
            "second" to Json.encodeToJsonElement(second.serialize()),
            "third" to Json.encodeToJsonElement(third.serialize())
        ))
    }

    override fun natualSerialize(): String {
//        return "(${left.natualSerialize()} ${symbol.natualSign} ${right.natualSerialize()})"
        return symbol.natualSign(first,second,third)
    }

    override fun getChild(): List<INode<*>> {
        return listOf(first,second,third)
    }

    @Deprecated("测试替换节点方法，不知道啥时候会删，谨慎使用")
    @Suppress("UNCHECKED_CAST")
    override fun <A> replaceNode(input: INode<A>, output: INode<A>): Boolean {
        var flag = false
        if (first == input) {
            first = output as INode<F>
            flag = true
        }
        if (second == input) {
            second = output as INode<S>
            flag = true
        }
        if (third == input) {
            third = output as INode<T>
            flag = true
        }
        return flag
    }
}