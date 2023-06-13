package org.co2dice.mirai.core.ast

import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.api.Symbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-16-10:19
 * @Message: Have a good time!  :)
 **/
object SymbolRegistry {

    private val symbolFactory = mutableMapOf<String, Symbol<*>>()


    fun <O : Any> deserialize(json: JsonObject): INode<O> {
        val symbol : Symbol<O> = getSymbol(json["symbol"].toString())
        return symbol.deserialize(json)
    }

    fun register(symbol: Symbol<*>) {
        symbolFactory[symbol::class.java.simpleName] = symbol
    }

    @Suppress("UNCHECKED_CAST")
    fun <S : Symbol<O>,O>  getSymbol(symbolName: String): S {
        //唯一的强转
        return symbolFactory[symbolName] as S
    }



}