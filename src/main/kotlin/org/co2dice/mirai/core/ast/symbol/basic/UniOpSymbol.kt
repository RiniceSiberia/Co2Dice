package org.co2dice.mirai.core.ast.symbol.basic

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.node.UniOpNode
import org.co2dice.mirai.core.ast.symbol.api.Symbol
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-04-21:44
 * @Message: Have a good time!  :)
 **/
abstract class UniOpSymbol<O : Any,I : Any> : Symbol<O> {

    abstract fun natualSign(input : INode<I>) : String

    open fun check(input: I) : O{
        return operation(input)
    }

    override fun deserialize(json: JsonObject): UniOpNode<O, I> {
        val nodeA: INode<I> = SymbolRegistry.deserialize(json.get("child").asJsonObject)
        return UniOpNode(this,nodeA)
    }
    abstract fun operation(input: I): O
}