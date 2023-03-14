package org.co2dice.mirai.ast

import org.co2dice.mirai.ast.node.api.AstNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-14-13:43
 * @Message: Have a good time!  :)
 **/
class Tree <T> : Function<T>{
    var root : AstNode<T>? = null
    //根节点返回值一定要是T

    fun getNode() : AstNode<T>?{
        return root
    }


    fun addNode(old : AstNode<*>, new : AstNode<*>) : Boolean{
        if(haveEmpty()){

        }else{
            return false
        }
    }
    fun invoke(param : Map<String,Any>) : T?{
        return try {
            root?.operation(param)
        }catch (e : Exception){
            e.printStackTrace()
            null
        }
    }

    fun haveEmpty() : Boolean{
        return root?.isEmpty() ?: true
    }

}