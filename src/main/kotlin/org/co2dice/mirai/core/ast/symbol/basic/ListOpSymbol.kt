package org.co2dice.mirai.core.ast.symbol.basic

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.node.ListOpNode
import org.co2dice.mirai.core.ast.symbol.api.Symbol
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-04-21:48
 * @Message: Have a good time!  :)
 **/
abstract class ListOpSymbol<O : Any, E : Any> : Symbol<O> {

    abstract fun natualSign(list : List<INode<out E>>) : String

    abstract fun check(list : List<E>) : O

    override fun deserialize(json: JsonObject): ListOpNode<O, E> {
        val nodes = mutableListOf<INode<E>>()
        for (i in json.get("children").asJsonArray) {
            nodes.add(SymbolRegistry.deserialize(i.asJsonObject))
        }
// 语法糖模式如下，为了代码可读性注了，引以为戒，不要乱用语法糖，该写类型还是得写类型
//        val nodes:List<INode<E>> = json.get("children").asJsonArray.map { SymbolRegistry.parseNode(it.asJsonObject) }

        return ListOpNode(this,nodes)
    }


    open fun operation(list : List<E>): O{
        return check(list)
    }

}