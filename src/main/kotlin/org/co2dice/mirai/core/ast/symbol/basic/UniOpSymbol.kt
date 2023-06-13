package org.co2dice.mirai.core.ast.symbol.basic

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.node.UniOpNode
import org.co2dice.mirai.core.ast.symbol.api.Symbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-04-21:44
 * @Message: Have a good time!  :)
 **/
abstract class UniOpSymbol<O : Any,I : Any> : Symbol<O> {

    init {
        SymbolRegistry.register(this)
    }
    abstract fun natualSign(input : INode<I>) : String
    abstract fun operation(input: I, params:Params): O

    open fun check(input: I, params:Params) : O{
        return operation(input,params)
    }

    override fun deserialize(json: JsonObject): UniOpNode<O, I> {
        val nodeA: INode<I> = SymbolRegistry.deserialize(json["child"]!!.jsonObject)
        return UniOpNode(this,nodeA)
    }
}