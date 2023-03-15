package org.co2dice.mirai.ast.node.api

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-15-19:13
 * @Message: Have a good time!  :)
 **/
abstract class ListChildNode<T> : AstNode<List<T>>() {

    var list = mutableListOf<AstNode<T>>()

    override fun getChild(): List<AstNode<*>> {
        return list
    }

    override fun vacancy(): Boolean {
        return true
    }

    fun addNode(node: AstNode<T>) {
        if (node !is PlaceholderNode){
            list.add(node)
        }
    }


}