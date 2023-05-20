package org.co2dice.mirai.core.ast.symbol.basic

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.ParamLeafNode
import org.co2dice.mirai.core.ast.symbol.api.Symbol
import kotlin.jvm.Throws
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-05-22:30
 * @Message: Have a good time!  :)
 **/
abstract class ParamLeafSymbol<O : Any> : Symbol<O> {

    abstract val clazz : KClass<*>

    open fun check(key : String, params: Params) : O{
        return operation(key, params)
    }

    abstract fun natualSign(key : String) : String

    override fun deserialize(json: JsonObject): ParamLeafNode<O> {
        val key = json.get("value").asJsonObject.asString
        return ParamLeafNode(this,key)
    }
    @Throws(NullPointerException::class)
    abstract fun operation(key: String, params: Params): O
}