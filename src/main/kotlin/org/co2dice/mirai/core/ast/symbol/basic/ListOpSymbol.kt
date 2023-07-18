package org.co2dice.mirai.core.ast.symbol.basic

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.node.ListOpNode
import org.co2dice.mirai.core.ast.symbol.api.Symbol

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-04-21:48
 * {@code @Message:} Have a good time!  :)
 **/
abstract class ListOpSymbol<O : Any, E : Any> : Symbol<O> {

    init {
        SymbolRegistry.register(this)
    }

    abstract fun natualSign(list : List<INode<out E>>) : String
    abstract fun operation(list: List<E>, params:Params) : O
    open fun check(list: List<E>, params:Params): O{
        return operation(list,params)
    }

    override fun deserialize(json: JsonObject): ListOpNode<O, E> {
        val nodes = mutableListOf<INode<out E>>()
        for (i in json["children"]?.jsonArray!!) {
            nodes.add(SymbolRegistry.deserialize(i.jsonObject))
        }
// 语法糖模式如下，为了代码可读性注了，引以为戒，不要乱用语法糖，该写类型还是得写类型
//        val nodes:List<INode<E>> = json.get("children").asJsonArray.map { SymbolRegistry.parseNode(it.asJsonObject) }

        return ListOpNode(this,nodes)
    }



}