package org.co2dice.mirai.core.ast.symbol.basic

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.BiOpNode
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.api.Symbol
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-31-21:40
 * @Message: Have a good time!  :)
 **/
abstract class BiOpSymbol<O : Any,L : Any,R : Any> : Symbol<O> {

    abstract fun natualSign(left : INode<L>,right : INode<R>) : String

    override fun deserialize(json: JsonObject): BiOpNode<O, L, R> {
        val nodeA:INode<L> = SymbolRegistry.deserialize(json.get("left").asJsonObject)
        val nodeB:INode<R> = SymbolRegistry.deserialize(json.get("right").asJsonObject)
        return BiOpNode(this,nodeA,nodeB)
    }

    abstract fun operation(l: L, r: R): O

    open fun check(l: L, r: R): O{
        return operation(l,r)
    }
}
