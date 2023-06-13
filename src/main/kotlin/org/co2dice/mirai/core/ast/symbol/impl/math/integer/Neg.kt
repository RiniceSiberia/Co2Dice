package org.co2dice.mirai.core.ast.symbol.impl.math.integer

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:54
 * @Message: Have a good time!  :)
 **/
object Neg : UniOpSymbol<Int, Int>(){
    override fun operation(item: Int, params:Params): Int {
        return -item
    }

    override fun natualSign(input: INode<Int>): String {
        return "-${input.natualSerialize()}"
    }
}