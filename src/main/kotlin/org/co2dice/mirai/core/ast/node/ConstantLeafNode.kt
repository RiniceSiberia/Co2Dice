package org.co2dice.mirai.core.ast.node

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.ConstantLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-01-13:35
 * @Message: Have a good time!  :)
 **/
class ConstantLeafNode<O : Any>(override var symbol: ConstantLeafSymbol<O>,
                                val value: O) : INode<O>, ISymbolHolder<ConstantLeafSymbol<O>> {
    override fun evaluate(params: Params): O {
        return symbol.operation(value)
    }

    override fun check(params: Params): O {
        return symbol.check(value)
    }

    override fun serialize(): JsonObject {
        val json = JsonObject()
        json.addProperty("symbol", symbol::class.java.simpleName)
        json.addProperty("value", value.toString())
        return json
    }

    override fun natualSerialize(): String {
//        return value.toString() + " : ${symbol.natualSign}"
        return symbol.natualSign(value)
    }

    override fun getChild(): List<INode<*>> {
        return listOf()
    }
}