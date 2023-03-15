package org.co2dice.mirai.ast.node.number

import org.co2dice.mirai.ast.node.api.ConstantNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-15-19:53
 * @Message: Have a good time!  :)
 **/
class NumberConstantNode(value : Int) : ConstantNode<Int>(value) {
    override val name: String = "number_constant_node"
}