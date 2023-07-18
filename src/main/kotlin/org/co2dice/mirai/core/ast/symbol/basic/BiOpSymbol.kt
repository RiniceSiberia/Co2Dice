package org.co2dice.mirai.core.ast.symbol.basic

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.BiOpNode
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.api.Symbol

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-03-31-21:40
 * {@code @Message:} Have a good time!  :)
 **/

abstract class BiOpSymbol<O : Any,L : Any,R : Any> : Symbol<O> {

    init {
        SymbolRegistry.register(this)
    }

    abstract fun natualSign(left : INode<out L>, right : INode<out R>) : String

    override fun deserialize(json: JsonObject): BiOpNode<O, L, R> {
        val nodeA: INode<L> = json["left"]?.let { SymbolRegistry.deserialize(it.jsonObject) }!!
        val nodeB: INode<R> = json["right"]?.let { SymbolRegistry.deserialize(it.jsonObject) }!!
        return BiOpNode(this,nodeA,nodeB)
    }

    abstract fun operation(l: L, r: R, params:Params): O

    open fun check(l: L, r: R, params:Params): O{
        return operation(l, r,params)
    }

}
