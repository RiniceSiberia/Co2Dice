package org.co2dice.mirai.ast.node.api

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-15-14:28
 * @Message: 占位符节点，无法计算稳定报错,无法加入到list节点中
 **/
abstract class PlaceholderNode<T> : AstNode<T>() {

    override fun operation(param: Map<String, Any>): T {
        throw Exception("无法对存在占位符的函数进行运算")
    }

    override fun vacancy(): Boolean {
        return true
    }

    override fun getChild(): List<AstNode<*>> {
        return emptyList()
    }
}