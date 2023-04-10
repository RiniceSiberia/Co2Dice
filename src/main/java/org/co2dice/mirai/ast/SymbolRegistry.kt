package org.co2dice.mirai.ast

import com.google.gson.JsonObject
import org.co2dice.mirai.ast.node.basic.INode
import org.co2dice.mirai.ast.symbol.api.Symbol
import org.co2dice.mirai.ast.symbol.impl.leaf.constant.BoolConstant

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-16-10:19
 * @Message: Have a good time!  :)
 **/
object SymbolRegistry {


    private val symbolFactory = mutableMapOf<String, Symbol<*>>()

    fun <O> deserialize(json:JsonObject):INode<O>{
        val symbol : Symbol<O> = getSymbol(json.get("symbol").asString)
        return symbol.deserialize(json)
    }

    fun register(symbol: Symbol<*>) {
        symbolFactory[symbol::class.java.name] = symbol
    }

    @SuppressWarnings("unchecked","unsafe")
    @Suppress("UNCHECKED_CAST")
    fun <O>  getSymbol(symbolName: String): Symbol<O> {
        //唯一的强转
        return symbolFactory[symbolName] as Symbol<O>
    }

    init {

    }
}