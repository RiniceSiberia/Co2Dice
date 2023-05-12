package org.co2dice.mirai.core.ast.node.basic

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.Params
import kotlin.jvm.Throws


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-17:46
 * @Message: 节点的基类，instance，可以被new
 **/
interface INode <
    O
    //输出
    > {
    @Throws(NullPointerException::class)
    fun evaluate(params: Params) : O

    fun serialize() : JsonObject

    fun natualSerialize() : String



    fun getChild() : List<INode<*>>
    //获取所有子节点

    fun dfs(find : (INode<*>) -> Boolean) : INode<*>? {
        //找寻第一个符合条件的节点
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

    fun competeDfs(find : (INode<*>) -> Boolean) : MutableList<INode<*>>{
        val result = mutableListOf<INode<*>>()
        if (find(this)) {
            result.add(this)
        }
        for (child in getChild()) {
            result.addAll(child.competeDfs(find))
        }
        return result
    }

}