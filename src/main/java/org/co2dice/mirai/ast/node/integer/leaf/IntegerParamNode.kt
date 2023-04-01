package org.co2dice.mirai.ast.node.integer.leaf

import org.co2dice.mirai.ast.node.integer.generic.NumberLeafNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-16-10:11
 * @Message: Have a good time!  :)
 **/
class IntegerParamNode(override var value: String = "x") : NumberLeafNode<String>() {
    override fun operation(param: Map<String, Any>): Int {
        return param[value] as Int
    }
}