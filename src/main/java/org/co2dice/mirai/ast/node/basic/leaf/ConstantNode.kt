package org.co2dice.mirai.ast.node.basic.leaf

import org.co2dice.mirai.ast.node.basic.LeafNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-14-13:15
 * @Message: Have a good time!  :)
 **/
abstract class ConstantNode<T>(
    val value : T
    ) : LeafNode<T>() {

    override fun operation(param: Map<String, Any>): T {
        return value
    }

    override fun toString(): String {
        return value.toString()
    }

}