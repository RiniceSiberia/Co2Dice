package org.co2dice.mirai.ast.node.number

import org.co2dice.mirai.ast.node.api.PlaceholderNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-15-14:32
 * @Message: Have a good time!  :)
 **/
class NumberPlaceholderNode : PlaceholderNode<Int>() {
    override val name: String = "number_placeholder_node"
}