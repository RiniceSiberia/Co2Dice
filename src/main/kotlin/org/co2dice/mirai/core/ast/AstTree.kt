package org.co2dice.mirai.core.ast

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.node.ParamLeafNode
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation
import org.co2dice.mirai.core.utils.situation.ResolutionSituation
import org.co2dice.mirai.core.utils.situation.ResultSituation
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

    //检查能不能跑
    fun check(params: Params) : Boolean{
        return try {
            root.check(params)
            true
        }catch (e : Exception){
            false
        }
    }

    private fun dfs(func : (INode<*>) -> Boolean) : List<INode<*>>{
        return root.competeDfs(func)
    }

    fun getParams() : List<Pair<String,KClass<*>>>{
        val params = dfs { it is ParamLeafNode }
        val list = mutableListOf<Pair<String,KClass<*>>>()
        for (param in params){
            val key = (param as ParamLeafNode).key
            val type : KClass<*> = param.symbol.clazz
            list.add(Pair(key,type))
        }
        return list
    }

    fun getParamsNoSituation() : List<Pair<String,KClass<*>>>{
        return getParams().filter {
            it.second != PreActivationSituation::class
                || it.second != ActivationSituation::class
                || it.second != ResolutionSituation::class
                || it.second != ResultSituation::class
        }
    }

}