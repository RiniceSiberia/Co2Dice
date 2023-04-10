package org.co2dice.mirai.ast.symbol.basic

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.co2dice.mirai.ast.node.ConstantLeafNode
import org.co2dice.mirai.ast.symbol.api.Symbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-05-13:31
 * @Message: Have a good time!  :)
 **/
abstract class ConstantLeafSymbol<O> : Symbol<O> {
    override fun deserialize(json: JsonObject): ConstantLeafNode<O> {
        val value = wrapper(json.get("value"))
        return ConstantLeafNode(this,value)
    }

    fun operation(input : O): O{
        return input
    }

    abstract fun wrapper(json: JsonElement): O
}