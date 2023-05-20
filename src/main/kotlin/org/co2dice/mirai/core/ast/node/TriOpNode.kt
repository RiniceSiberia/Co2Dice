package org.co2dice.mirai.core.ast.node

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.TriOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-24-23:02
 * @Message: Have a good time!  :)
 **/
class TriOpNode<O : Any,F : Any,S : Any,T : Any>(
    override var symbol: TriOpSymbol<O,F,S,T>,
    val first: INode<F>,
    val second: INode<S>,
    val third: INode<T>
) : INode<O>, ISymbolHolder<TriOpSymbol<O,F,S,T>> {
    override fun evaluate(params: Params): O {
        val f = first.evaluate(params)
        val s = second.evaluate(params)
        val t = third.evaluate(params)
        return symbol.operation(f,s,t)
    }

    override fun check(params: Params): O {
        return symbol.check(first.check(params),second.check(params),third.check(params))
    }

    override fun serialize(): JsonObject {
        val json = JsonObject()
        json.addProperty("symbol", symbol::class.java.simpleName)
        json.add("first", first.serialize())
        json.add("second", second.serialize())
        json.add("third", third.serialize())
        return json
    }

    override fun natualSerialize(): String {
//        return "(${left.natualSerialize()} ${symbol.natualSign} ${right.natualSerialize()})"
        return symbol.natualSign(first,second,third)
    }

    override fun getChild(): List<INode<*>> {
        return listOf(first,second,third)
    }
}