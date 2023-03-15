package org.co2dice.mirai.ast.node.api


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-17:46
 * @Message: Have a good time!  :)
 **/
abstract class AstNode <
    O
    //输出
    > {
    abstract val name : String

    abstract fun operation(param : Map<String,Any>):O
        //这个节点的运算方式，计算这个节点的运算结果

    abstract fun vacancy() : Boolean
        //用来检查能否被插入一个新节点

    abstract fun getChild() : List<AstNode<*>>
    //获取所有子节点

    fun dfs(find : () -> Boolean) : AstNode<*>? {
        if (find()) {
            return this
        }
        for (child in getChild()) {
            val result = child.dfs(find)
            if (result != null) {
                return result
            }
        }
        return null
    }

}