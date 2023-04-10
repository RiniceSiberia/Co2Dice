package org.co2dice.mirai.ast.symbol.basic

import com.google.gson.JsonObject
import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.node.BiOpNode
import org.co2dice.mirai.ast.node.basic.INode
import org.co2dice.mirai.ast.symbol.api.Symbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-31-21:40
 * @Message: Have a good time!  :)
 **/
abstract class BiOpSymbol<O,L,R> : Symbol<O> {
    override fun deserialize(json: JsonObject): BiOpNode<O, L, R> {
        val nodeA:INode<L> = SymbolRegistry.deserialize(json.get("left").asJsonObject)
        val nodeB:INode<R> = SymbolRegistry.deserialize(json.get("right").asJsonObject)
        return BiOpNode(this,nodeA,nodeB)
    }

    abstract fun operation(l: L, r: R): O
}
