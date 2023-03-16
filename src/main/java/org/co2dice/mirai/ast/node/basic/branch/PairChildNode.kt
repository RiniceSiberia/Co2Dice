package org.co2dice.mirai.ast.node.basic.branch

import org.co2dice.mirai.ast.node.basic.AstNode
import org.co2dice.mirai.ast.node.basic.BranchNode
import org.co2dice.mirai.ast.node.basic.leaf.PlaceholderNode


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-14-13:16
 * @Message: Have a good time!  :)
 **/
abstract class PairChildNode<
    LI,
    //左输入，left input
    RI,
    //右输入,
    O> : BranchNode<O>() {

    //example:
    //(7*(3+2) / 6)^2 ->
    //       /
    //    |     |
    //    *     6
    //  |   |
    //  7   +
    //     3  2
    abstract var left : AstNode<LI>
    //左节点
    abstract var right : AstNode<RI>
    //右节点

    override fun vacancy(): Boolean {
        return (left is PlaceholderNode) || (right is PlaceholderNode)
    }
    override fun getChild(): List<AstNode<*>> {
        return listOfNotNull(left,right)
    }


}