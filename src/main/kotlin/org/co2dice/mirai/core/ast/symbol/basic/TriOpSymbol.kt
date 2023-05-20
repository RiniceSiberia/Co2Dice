package org.co2dice.mirai.core.ast.symbol.basic

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.TriOpNode
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.api.Symbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-24-22:58
 * @Message: Have a good time!  :)
 **/
abstract class TriOpSymbol<O : Any,F : Any,S : Any,T : Any> : Symbol<O> {

    abstract fun natualSign(first : INode<F>,second : INode<S>,third : INode<T>) : String

    open fun check (f : F,s : S, t : T) : O{
        return operation(f,s,t)
    }

    override fun deserialize(json: JsonObject): TriOpNode<O, F, S, T> {
        val firstNode:INode<F> = SymbolRegistry.deserialize(json.get("first").asJsonObject)
        val secondNode:INode<S> = SymbolRegistry.deserialize(json.get("second").asJsonObject)
        val thirdNode:INode<T> = SymbolRegistry.deserialize(json.get("third").asJsonObject)
        return TriOpNode(this,firstNode,secondNode,thirdNode)
    }

    abstract fun operation(f : F,s : S, t : T): O

}