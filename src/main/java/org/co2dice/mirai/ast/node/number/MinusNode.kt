package org.co2dice.mirai.ast.node.number

import org.co2dice.mirai.ast.node.api.AstNode
import org.co2dice.mirai.ast.node.api.PairChildNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-18:04
 * @Message: Have a good time!  :)
 **/
class MinusNode(
    override var left : AstNode<Int>?,
    override var right : AstNode<Int>?,
) : PairChildNode<Int, Int, Int>() {

    override val name: String = "minus_node"

    override fun operation(param : Map<String,Any>) : Int{
        return left!!.operation(param) - right!!.operation(param)
    }



}