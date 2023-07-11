package org.co2dice.mirai.core.ast.symbol.impl.math.bool

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:36
 * @Message: Have a good time!  :)
 **/
object Greater : BiOpSymbol<Boolean, Int, Int>(){
    override fun operation(l: Int, r: Int, params:Params): Boolean {
        return l > r
    }

    override fun natualSign(left: INode<out Int>, right: INode<out Int>): String {
        return "(${left.natualSerialize()} > ${right.natualSerialize()})"
    }

}