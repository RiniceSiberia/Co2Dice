package org.co2dice.mirai.core.ast.symbol.basic

import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.SituationLeafNode
import org.co2dice.mirai.core.ast.symbol.api.Symbol
import org.co2dice.mirai.core.utils.situation.SituationApi

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-05-22:30
 * {@code @Message:} 特殊的"情景"叶子节点，专门用于存储情景，一个param只能有一个
 **/
abstract class SituationLeafSymbol<O : SituationApi> : Symbol<O> {

    init {
        SymbolRegistry.register(this)
    }

    @Suppress("UNCHECKED_CAST")
    open fun operation(params:Params): O{
        return params.situation as O
    }

    open fun check( params:Params) : O{
        return operation(params)
    }

    abstract fun natualSign() : String

    override fun deserialize(json: JsonObject): SituationLeafNode<O> {
        return SituationLeafNode(this)
    }
}