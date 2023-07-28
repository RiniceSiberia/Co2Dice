package org.co2dice.mirai.core.ast.symbol.impl.genericity

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-21-17:53
 * @Message: Have a good time!  :)
 **/
object SetSizeSymbol : UniOpSymbol<Int,Set<*>>() {
    override fun natualSign(input: INode<out Set<*>>): String {
        return "${input.natualSerialize()}.size()"
    }

    override fun operation(input: Set<*>, params: Params): Int {
        return input.size
    }
}