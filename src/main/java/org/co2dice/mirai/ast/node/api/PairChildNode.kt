package org.co2dice.mirai.ast.node.api

import com.google.gson.Gson
import com.google.gson.JsonElement

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-14-13:16
 * @Message: Have a good time!  :)
 **/
abstract class PairChildNode<
    LI,
    //左输入，left input
    RI,
    //右输入,
    O> : AstNode<O>() {

    //example:
    //(7*(3+2) / 6)^2 ->
    //       /
    //    |     |
    //    *     6
    //  |   |
    //  7   +
    //     3  2
    abstract var left : AstNode<LI>?
    //左节点
    abstract var right :  AstNode<RI>?

    override fun toJSONElement(): JsonElement {
        return Gson().toJsonTree(mapOf(
            "node_name" to Gson().toJson(this.name),
            "left_node" to left!!.toJSONElement(),
            "right_node" to right!!.toJSONElement()
            ))
    }
    override fun isEmpty(): Boolean {
        return (left?.isEmpty() ?: true) || (right?.isEmpty() ?: true)
    }
}