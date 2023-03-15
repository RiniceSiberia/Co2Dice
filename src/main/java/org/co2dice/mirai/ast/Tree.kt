package org.co2dice.mirai.ast

import org.co2dice.mirai.ast.node.api.AstNode
import org.co2dice.mirai.ast.node.api.PairChildNode
import org.co2dice.mirai.ast.node.api.PlaceholderNode

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-14-13:43
 * @Message: Have a good time!  :)
 **/
class Tree <T>(
    var root : AstNode<T>
) : Function<T>{

    //根节点返回值一定要是T

    fun <O,X>addNode(old : AstNode<O>, new : AstNode<X>) : Boolean{
        if(old.vacancy()){
            //有空位,可插入替代
            if (old is PairChildNode<*,*,*>){
                if (old.left is PlaceholderNode && old.left){
                    old.left = new
                    return true
                }
            }
        }else{
            return false
        }
    }
    fun invoke(param : Map<String,Any>) : T?{
        return try {
            root.operation(param)
        }catch (e : Exception){
            e.printStackTrace()
            null
        }
    }

    fun haveEmpty() : Boolean{
        return root.vacancy()
    }

    fun dfs(find : () -> Boolean) : AstNode<*>? {
        return root.dfs(find)
    }

}