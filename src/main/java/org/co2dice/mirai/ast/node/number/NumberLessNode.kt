package org.co2dice.mirai.ast.node.number

import org.co2dice.mirai.ast.node.api.AstNode
import org.co2dice.mirai.ast.node.api.PairChildNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-14-20:04
 * @Message: Have a good time!  :)
 **/
class NumberLessNode (
    override var left : AstNode<Int> = NumberPlaceholderNode(),
    override var right : AstNode<Int> = NumberPlaceholderNode(),
) : PairChildNode<Int, Int, Boolean>() {

    override val name: String = "number_less_node"

    override fun operation(param : Map<String,Any>) : Boolean{
        return left.operation(param) < right.operation(param)
    }
}