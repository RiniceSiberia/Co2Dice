package org.co2dice.mirai.ast.node.number

import org.co2dice.mirai.ast.node.api.AstNode
import org.co2dice.mirai.ast.node.generic.EqualsNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-14-16:23
 * @Message: Have a good time!  :)
 **/
class NumberEqualsNode(
    left : AstNode<Int>?,
    right : AstNode<Int>?,
) : EqualsNode<Int>(left,right) {
    override val name: String = "number_equals"
}