package org.co2dice.mirai.ast.node.integer.leaf

import org.co2dice.mirai.ast.node.integer.generic.NumberLeafNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-15-19:53
 * @Message: Have a good time!  :)
 **/
class IntegerConstantNode(override var value: Int) : NumberLeafNode<Int>() {
    override fun operation(param: Map<String, Any>): Int {
        return value
    }
}