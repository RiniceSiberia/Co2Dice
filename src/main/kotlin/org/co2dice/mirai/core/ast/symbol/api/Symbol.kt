package org.co2dice.mirai.core.ast.symbol.api

import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.core.ast.node.INode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-31-21:39
 * @Message: 符号的基类，和node并行，结构上属于prototype
 **/
interface Symbol<O : Any> {

    fun deserialize(json: JsonObject): INode<O>


}