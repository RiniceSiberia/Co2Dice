package org.co2dice.mirai.core.ast.node

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.symbol.basic.UniOpFunctionSymbol
import org.co2dice.mirai.core.ast.tree.AstTree

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-05-23:06
 * @Message: Have a good time!  :)
 **/
class UniOpFunctionNode<O : Any,I : Any,A : Any>(
    override var symbol: UniOpFunctionSymbol<O, I, A>,
    var obj: INode<I>,
    var function : AstTree,
    //O : 输出类型
    //I : 处理使用的类型
    //A : Ast执行使用的类型
) : INode<O>, ISymbolHolder<UniOpFunctionSymbol<O, I, A>> {
    override fun evaluate(params: Params): O {
        return symbol.operation(obj.evaluate(params),function,params)
    }

    override fun check(params: Params): O {
        return symbol.check(obj.check(params),function,params)
    }

    override fun serialize(): JsonObject {
        return JsonObject(mapOf(
            "symbol" to Json.encodeToJsonElement(symbol::class.java.simpleName),
            "obj" to Json.encodeToJsonElement(obj.serialize()),
            "function" to Json.encodeToJsonElement(function.serialize())
        ))
    }

    override fun natualSerialize(): String {
//        return "(${left.natualSerialize()} ${symbol.natualSign} ${right.natualSerialize()})"
        return symbol.natualSign(obj,function)
    }

    override fun getChild(): List<INode<*>> {
        return listOf(obj)
    }

    @Deprecated("测试替换节点方法，不知道啥时候会删，谨慎使用")
    @Suppress("UNCHECKED_CAST")
    override fun <T> replaceNode(input: INode<T>, output: INode<T>): Boolean {
        //只能换obj
        if (obj == input) {
            obj = output as INode<I>
            return true
        }
        return false
    }
}