package org.co2dice.mirai.core.ast.symbol.basic

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
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

    init {
        SymbolRegistry.register(this)
    }

    abstract val clazz : KClass<*>
    @Throws(NullPointerException::class)
    abstract fun operation(key: String, params:Params): O

    open fun check(key : String, params:Params) : O{
        return operation(key, params)
    }

    abstract fun natualSign(key : String) : String

    override fun deserialize(json: JsonObject): ParamLeafNode<O> {
        val key = json["value"]!!.jsonObject.toString()
        return ParamLeafNode(this,key)
    }
}