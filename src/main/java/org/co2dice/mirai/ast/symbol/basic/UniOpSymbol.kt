package org.co2dice.mirai.ast.symbol.basic

import com.google.gson.JsonObject
import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.node.basic.INode
import org.co2dice.mirai.ast.node.UniOpNode
import org.co2dice.mirai.ast.symbol.api.Symbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-04-21:44
 * @Message: Have a good time!  :)
 **/
abstract class UniOpSymbol<O,I> : Symbol<O> {
    override fun deserialize(json: JsonObject): UniOpNode<O, I> {
        val nodeA: INode<I> = SymbolRegistry.deserialize(json.get("child").asJsonObject)
        return UniOpNode(this,nodeA)
    }
    abstract fun operation(item: I): O
}