package org.co2dice.mirai.ast.node

import com.google.gson.JsonObject
import org.co2dice.mirai.ast.Params
import org.co2dice.mirai.ast.node.api.ISymbolHolder
import org.co2dice.mirai.ast.node.basic.INode
import org.co2dice.mirai.ast.symbol.basic.ParamLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-05-22:29
 * @Message: Have a good time!  :)
 **/
class ParamLeafNode<O>(override val symbol: ParamLeafSymbol<O>,
                       val key: String) : INode<O>, ISymbolHolder<ParamLeafSymbol<O>> {
    override fun evaluate(params: Params): O {
        return symbol.operation(key,params)
    }

    override fun serialize(): JsonObject {
        val json = JsonObject()
        json.addProperty("symbol", symbol::class.java.name)
        json.addProperty("key", key)
        return json
    }

    override fun natualSerialize(): String {
        return "param(type : ${symbol.natualSign},key : $key)"
    }
}