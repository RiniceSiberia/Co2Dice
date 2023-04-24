package org.co2dice.mirai.core.ast.node

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-05-22:29
 * @Message: Have a good time!  :)
 **/
class ParamLeafNode<O : Any>(override var symbol: ParamLeafSymbol<O>,
                             val key: String) : INode<O>, ISymbolHolder<ParamLeafSymbol<O>> {
    override fun evaluate(params: Params): O {
        return symbol.operation(key, params)
    }

    override fun serialize(): JsonObject {
        val json = JsonObject()
        json.addProperty("symbol", symbol::class.java.simpleName)
        json.addProperty("key", key)
        return json
    }

    override fun natualSerialize(): String {
//        return "param(type :: ${symbol.natualSign},key = $key)"
        return symbol.natualSign(key)
    }

    override fun getChild(): List<INode<*>> {
        return listOf()
    }
}