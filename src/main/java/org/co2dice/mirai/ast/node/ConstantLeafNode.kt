package org.co2dice.mirai.ast.node

import com.google.gson.JsonObject
import org.co2dice.mirai.ast.Params
import org.co2dice.mirai.ast.node.api.ISymbolHolder
import org.co2dice.mirai.ast.node.basic.INode
import org.co2dice.mirai.ast.symbol.basic.ConstantLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-01-13:35
 * @Message: Have a good time!  :)
 **/
class ConstantLeafNode<O>(override val symbol: ConstantLeafSymbol<O>,
                          val value: O) : INode<O>, ISymbolHolder<ConstantLeafSymbol<O>> {
    override fun evaluate(params: Params): O {
        return symbol.operation(value)
    }

    override fun serialize(): JsonObject {
        val json = JsonObject()
        json.addProperty("symbol", symbol::class.java.name)
        json.addProperty("value", value.toString())
        return json
    }

    override fun natualSerialize(): String {
        return value.toString() + " : ${symbol.natualSign}"
    }
}