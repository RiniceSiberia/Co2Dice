package org.co2dice.mirai.ast.node.basic

import com.google.gson.JsonElement


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-17:46
 * @Message: Have a good time!  :)
 **/
interface AstNode <
    O
    //输出
    > {

    fun operation(param : Map<String,Any>):O
        //这个节点的运算方式，计算这个节点的运算结果

    fun vacancy() : Boolean
        //用来检查能否被插入一个新节点

    fun getChild() : List<AstNode<*>>
    //获取所有子节点

    fun dfs(find : (AstNode<*>) -> Boolean) : AstNode<*>? {
        if (find(this)) {
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

    fun competeDfs(find : (AstNode<*>) -> Boolean) : MutableList<AstNode<*>>{
        val result = mutableListOf<AstNode<*>>()
        if (find(this)) {
            result.add(this)
        }
        for (child in getChild()) {
            result.addAll(child.competeDfs(find))
        }
        return result
    }

}