package org.co2dice.mirai.core.ast.symbol.basic

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.node.UniOpFunctionNode
import org.co2dice.mirai.core.ast.symbol.api.Symbol
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.utils.ConstantUtils.IT

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-05-23:05
 * {@code @Message:} Have a good time!  :)
 **/
abstract class UniOpFunctionSymbol<O : Any,I : Any,A : Any> : Symbol<O> {

    init {
        SymbolRegistry.register(this)
    }

    abstract fun natualSign(obj : INode<I>, function : AstTree) : String

    override fun deserialize(json: JsonObject): UniOpFunctionNode<O, I, A> {
        val obj: INode<I> = json["obj"]?.let { SymbolRegistry.deserialize(it.jsonObject) }!!
        val function: AstTree = json["function"]?.let { AstTree(it.jsonObject) }!!
        return UniOpFunctionNode(this,obj,function)
    }

    abstract fun operation(obj : I,function : AstTree, params: Params): O

    open fun check(obj: I, function: AstTree , params: Params): O{
        return operation(obj, function,params)
    }

    fun execute(it : Any, function : AstTree, params: Params): A?{
        val interimParam = params.copy()
        interimParam.add(IT,it)
        val result = function.execute<A>(interimParam)
        interimParam.remove(IT)
        return result
    }

}