package org.co2dice.mirai.core.ast.node

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-01-13:33
 * {@code @Message:} Have a good time!  :)
 **/
class BiOpNode<O : Any,L : Any,R : Any>(
    override var symbol: BiOpSymbol<O, L, R>,
    var left: INode<out L>,
    var right: INode<out R>
) : INode<O>, ISymbolHolder<BiOpSymbol<O, L, R>> {
    override fun evaluate(params:Params): O {
        val l = left.evaluate(params)
        val r = right.evaluate(params)
        return symbol.operation(l,r,params)
    }

    override fun check(params:Params): O {
        val l = left.check(params)
        val r = right.check(params)
        return symbol.check(l,r,params)
    }

    override fun serialize(): JsonObject {
//        val json = JsonObject()
//        json.addProperty("symbol", symbol::class.java.simpleName)
//        json.add("left", left.serialize())
//        json.add("right", right.serialize())
        return JsonObject(mapOf(
            "symbol" to Json.encodeToJsonElement(symbol::class.java.simpleName),
            "left" to Json.encodeToJsonElement(left.serialize()),
            "right" to Json.encodeToJsonElement(right.serialize())
        ))
    }

    override fun natualSerialize(): String {
//        return "(${left.natualSerialize()} ${symbol.natualSign} ${right.natualSerialize()})"
        return symbol.natualSign(left,right)
    }

    override fun getChild(): List<INode<*>> {
        return listOf(left,right)
    }

    @Deprecated("测试替换节点方法，不知道啥时候会删，谨慎使用")
    @Suppress("UNCHECKED_CAST")
    override fun <T> replaceNode(input: INode<T>, output: INode<T>): Boolean {
        if (left == input) {
            left = output as INode<L>
            return true
        }else if (right == input) {
            right = output as INode<R>
            return true
        }
        return false
    }
}