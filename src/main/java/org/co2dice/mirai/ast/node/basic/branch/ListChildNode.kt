package org.co2dice.mirai.ast.node.basic.branch

import org.co2dice.mirai.ast.node.basic.AstNode
import org.co2dice.mirai.ast.node.basic.BranchNode
import org.co2dice.mirai.ast.node.basic.leaf.PlaceholderNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-15-19:13
 * @Message: Have a good time!  :)
 **/
abstract class ListChildNode<I,T>(
    var childs: MutableList<AstNode<I>> = mutableListOf()
) : BranchNode<List<T>>() {

    override fun getChild(): List<AstNode<I>> {
        return childs
    }

    override fun vacancy(): Boolean {
        return true
    }

}