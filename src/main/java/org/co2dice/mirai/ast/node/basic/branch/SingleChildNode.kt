package org.co2dice.mirai.ast.node.basic.branch

import org.co2dice.mirai.ast.node.basic.AstNode
import org.co2dice.mirai.ast.node.basic.BranchNode
import org.co2dice.mirai.ast.node.basic.leaf.PlaceholderNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-16-0:14
 * @Message: Have a good time!  :)
 **/
abstract class SingleChildNode<I,O> : BranchNode<O>() {
    abstract var child : AstNode<I>

    override fun vacancy(): Boolean {
        return child !is PlaceholderNode
    }

    override fun getChild(): List<AstNode<I>> {
        return listOf(child)
    }

}