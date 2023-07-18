package org.co2dice.mirai.core.ast.symbol.basic

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.node.TriOpNode
import org.co2dice.mirai.core.ast.symbol.api.Symbol

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-24-22:58
 * {@code @Message:} Have a good time!  :)
 **/
abstract class TriOpSymbol<O : Any,F : Any,S : Any,T : Any> : Symbol<O> {

    init {
        SymbolRegistry.register(this)
    }

    abstract fun natualSign(first : INode<out F>, second : INode<out S>, third : INode<out T>) : String

    abstract fun operation(f: F, s: S, t: T, params:Params): O

    open fun check (f: F, s: S, t: T, params:Params) : O{
        return operation(f, s, t,params)
    }

    override fun deserialize(json: JsonObject): TriOpNode<O, F, S, T> {
        val firstNode: INode<F> = SymbolRegistry.deserialize(json["first"]!!.jsonObject)
        val secondNode: INode<S> = SymbolRegistry.deserialize(json["second"]!!.jsonObject)
        val thirdNode: INode<T> = SymbolRegistry.deserialize(json["third"]!!.jsonObject)
        return TriOpNode(this,firstNode,secondNode,thirdNode)
    }

}