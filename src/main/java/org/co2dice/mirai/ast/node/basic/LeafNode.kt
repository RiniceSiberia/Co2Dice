package org.co2dice.mirai.ast.node.basic

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-16-0:05
 * @Message: 没有子节点的叶子
 **/
abstract class LeafNode<T>() : AstNode<T>() {

    override fun vacancy(): Boolean {
        return false
    }

    override fun getChild(): List<AstNode<*>> {
        return emptyList()
    }
}