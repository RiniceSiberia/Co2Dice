package org.co2dice.mirai.core.ast.node

import com.google.gson.JsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.api.ISymbolHolder
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-01-13:34
 * @Message: Have a good time!  :)
 **/
class UniOpNode<O : Any,I : Any>(
    override var symbol: UniOpSymbol<O, I>,
    val child: INode<I>
    ) : INode<O>, ISymbolHolder<UniOpSymbol<O, I>> {
    override fun evaluate(params: Params): O {
        return symbol.operation(child.evaluate(params))
    }

    override fun serialize(): JsonObject {
        val json = JsonObject()
        json.addProperty("symbol", symbol::class.java.simpleName)
        json.add("child", child.serialize())
        return json
    }

    override fun natualSerialize(): String {
//        return "(${symbol.natualSign} ${child.natualSerialize()})"
        return symbol.natualSign(child)
    }

    override fun getChild(): List<INode<*>> {
        return listOf(child)
    }

}