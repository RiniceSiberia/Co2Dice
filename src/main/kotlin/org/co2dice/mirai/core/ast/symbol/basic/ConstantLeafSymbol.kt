package org.co2dice.mirai.core.ast.symbol.basic

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.ConstantLeafNode
import org.co2dice.mirai.core.ast.symbol.api.Symbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-05-13:31
 * @Message: Have a good time!  :)
 **/
abstract class ConstantLeafSymbol<O : Any> : Symbol<O> {

    init {
        SymbolRegistry.register(this)
    }

    abstract fun natualSign(value : O) : String

    fun operation(input : O): O{
        return input
    }

    open fun check(value : O) : O{
        return operation(value)
    }


    override fun deserialize(json: JsonObject): ConstantLeafNode<O> {
        val value = json["value"]?.let { wrapper(it) }!!
        return ConstantLeafNode(this,value)
    }


    abstract fun wrapper(json: JsonElement): O

    open fun unwrap(value : O): JsonElement{
        return Json.encodeToJsonElement(value.toString())
    }
}