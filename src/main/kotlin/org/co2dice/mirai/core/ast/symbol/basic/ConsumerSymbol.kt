package org.co2dice.mirai.core.ast.symbol.basic

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.ConsumerNode
import org.co2dice.mirai.core.ast.symbol.api.Symbol
import org.co2dice.mirai.core.ast.tree.AstTree

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-08-12:25
 * @Message: Have a good time!  :)
 **/
abstract class ConsumerSymbol<O : Any,A : Any> : Symbol<O> {

    init {
        SymbolRegistry.register(this)
    }

    abstract fun natualSign(function : AstTree) : String

    override fun deserialize(json: JsonObject): ConsumerNode<O, A> {
        val function: AstTree = json["function"]?.let { AstTree(it.jsonObject) }!!
        return ConsumerNode(this,function)
    }

    abstract fun operation(function : AstTree, params: Params): O

    fun execute(function : AstTree, params: Params): A?{
        return function.execute(params)
    }

    open fun check(function: AstTree, params: Params): O{
        return operation(function,params)
    }
}