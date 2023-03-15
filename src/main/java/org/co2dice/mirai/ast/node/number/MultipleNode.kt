package org.co2dice.mirai.ast.node.number

import org.co2dice.mirai.ast.node.api.AstNode
import org.co2dice.mirai.ast.node.api.PairChildNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-18:05
 * @Message: Have a good time!  :)
 **/
class MultipleNode(
    override var left : AstNode<Int> = NumberPlaceholderNode(),
    override var right : AstNode<Int> = NumberPlaceholderNode(),
) : PairChildNode<Int, Int, Int>() {

    override val name: String = "multiple_node"

    override fun operation(param : Map<String,Any>) : Int{
        return left!!.operation(param) * right!!.operation(param)
    }
}