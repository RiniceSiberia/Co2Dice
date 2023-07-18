package org.co2dice.mirai.core.ast.symbol.impl.math.bool

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-09-18:54
 * {@code @Message:} Have a good time!  :)
 **/
object Not : UniOpSymbol<Boolean, Boolean>(){
    override fun operation(item: Boolean, params:Params): Boolean {
        return !item
    }

    override fun natualSign(input: INode<out Boolean>): String {
        return "!${input.natualSerialize()}"
    }
}