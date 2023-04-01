package org.co2dice.mirai.ast.node.bool

import com.google.gson.annotations.JsonAdapter
import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.node.basic.generic.NumberNode
import org.co2dice.mirai.ast.node.bool.generic.BoolBranchNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-14-16:25
 * @Message: Have a good time!  :)
 **/
class GreaterNode (override val left: NumberNode,
                   override val right: NumberNode) : BoolBranchNode<Int,Int>() {
    override fun operation(param: Map<String, Any>): Boolean {
        return left.operation(param) > right.operation(param)
    }
}