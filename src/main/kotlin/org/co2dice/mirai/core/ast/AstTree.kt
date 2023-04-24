package org.co2dice.mirai.core.ast

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.node.ParamLeafNode
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.bean.effect.utils.Situation
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-14-12:29
 * @Message: Have a good time!  :)
 **/
class AstTree<O : Any> (private var root : INode<O>){
    constructor(json :JsonObject) : this(
        root = SymbolRegistry.deserialize(json)
    )

    fun execute(params : Params) : O?{
        try {
            return root.evaluate(params)
        }catch (e : Exception){
            e.printStackTrace()
        }
        return null
    }

    private fun dfs(func : (INode<*>) -> Boolean) : List<INode<*>>{
        return root.competeDfs(func)
    }


}