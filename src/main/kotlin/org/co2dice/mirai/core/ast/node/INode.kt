package org.co2dice.mirai.core.ast.node

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.core.ast.Params
import kotlin.jvm.Throws


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-13-17:46
 * @Message: 节点的基类，instance，可以被new
 **/
sealed interface INode <
    O
    //输出
    > {

    @Throws(NullPointerException::class)
    fun evaluate(params:Params) : O

    @Throws(NullPointerException::class)
    fun check(params:Params) : O

    fun serialize() : JsonObject

    fun natualSerialize() : String



    fun getChild() : List<INode<*>>
    //获取所有子节点

    @Deprecated("测试替换节点方法，不知道啥时候会删，谨慎使用")
    fun<T> replaceNode(input : INode<T>, output : INode<T>) : Boolean

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

    fun getParams() : List<ParamLeafNode<*>>{
        return flatMap().filterIsInstance<ParamLeafNode<*>>()
    }

    fun flatMap() : List<INode<*>>{
        val result = mutableListOf<INode<*>>()
        result.add(this)
        for (child in getChild()) {
            result.addAll(child.flatMap())
        }
        return result.distinct()
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