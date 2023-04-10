package org.co2dice.mirai.ast.symbol.basic

import com.google.gson.JsonObject
import org.co2dice.mirai.ast.Params
import org.co2dice.mirai.ast.node.ParamLeafNode
import org.co2dice.mirai.ast.symbol.api.Symbol
import kotlin.jvm.Throws

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-05-22:30
 * @Message: Have a good time!  :)
 **/
abstract class ParamLeafSymbol<O> : Symbol<O> {
    override fun deserialize(json: JsonObject): ParamLeafNode<O> {
        val key = json.get("value").asJsonObject.asString
        return ParamLeafNode(this,key)
    }
    @Throws(NullPointerException::class)
    fun operation(key : String, params : Params): O{
        return params.get<O>(key)!!
    }
}