package org.co2dice.mirai.ast.node.number.leaf

import org.co2dice.mirai.ast.LeafEnum
import org.co2dice.mirai.ast.node.basic.leaf.ConstantNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-15-19:53
 * @Message: Have a good time!  :)
 **/
class NumberConstantNode(value : Int) : ConstantNode<Int>(value) {
    override val name: String = LeafEnum.NUMBER_CONSTANT.getName()

    override fun toString(): String {
        return value.toString()
    }
}