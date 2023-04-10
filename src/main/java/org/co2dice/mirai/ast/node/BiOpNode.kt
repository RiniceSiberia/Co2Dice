package org.co2dice.mirai.ast.node

import com.google.gson.JsonObject
import org.co2dice.mirai.ast.Params
import org.co2dice.mirai.ast.node.api.ISymbolHolder
import org.co2dice.mirai.ast.node.basic.INode
import org.co2dice.mirai.ast.symbol.basic.BiOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-01-13:33
 * @Message: Have a good time!  :)
 **/
class BiOpNode<O,L,R>(
    override val symbol: BiOpSymbol<O, L, R>,
    val left: INode<L>,
    val right: INode<R>
) : INode<O>, ISymbolHolder<BiOpSymbol<O, L, R>> {
    override fun evaluate(params: Params): O {
        val l = left.evaluate(params)
        val r = right.evaluate(params)
        return symbol.operation(l,r)
    }

    override fun serialize(): JsonObject {
        val json = JsonObject()
        json.addProperty("symbol", symbol::class.java.name)
        json.add("left", left.serialize())
        json.add("right", right.serialize())
        return json
    }

    override fun natualSerialize(): String {
        return "(${left.natualSerialize()} ${symbol.natualSign} ${right.natualSerialize()})"
    }

}