package org.co2dice.mirai.ast.node.integer

import org.co2dice.mirai.ast.node.basic.generic.NumberNode
import org.co2dice.mirai.ast.node.integer.generic.PairIntBranchNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-18:05
 * @Message: Have a good time!  :)
 **/
class IntegerMultiplyNode(
    left: NumberNode,
    right: NumberNode
) : PairIntBranchNode(left,right) {

    override fun operation(param : Map<String,Any>) : Int{
        return left.operation(param) * right.operation(param)
    }

}