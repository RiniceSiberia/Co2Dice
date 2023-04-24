package org.co2dice.mirai.core.ast

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.api.Symbol
import org.co2dice.mirai.core.ast.symbol.basic.*
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-16-10:19
 * @Message: Have a good time!  :)
 **/
object SymbolRegistry {

    private val symbolFactory = mutableMapOf<String, Symbol<*>>()

    fun <O : Any> deserialize(json:JsonObject):INode<O>{
        val symbol : Symbol<O> = getSymbol(json.get("symbol").asString)
        return symbol.deserialize(json)
    }

    fun register(symbol: Symbol<*>) {
        symbolFactory[symbol::class.java.simpleName] = symbol
    }

    @Suppress("UNCHECKED_CAST")
    fun <O : Any>  getSymbol(symbolName: String): Symbol<O> {
        //唯一的强转
        return symbolFactory[symbolName] as Symbol<O>
    }



}