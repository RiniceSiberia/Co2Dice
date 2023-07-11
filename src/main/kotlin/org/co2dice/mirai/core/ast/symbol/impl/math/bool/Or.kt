package org.co2dice.mirai.core.ast.symbol.impl.math.bool

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:54
 * @Message: Have a good time!  :)
 **/
object Or : BiOpSymbol<Boolean, Boolean, Boolean>(){
    override fun operation(l: Boolean, r: Boolean, params:Params): Boolean {
        return l || r
    }

    override fun natualSign(left: INode<out Boolean>, right: INode<out Boolean>): String {
        return "(${left.natualSerialize()} || ${right.natualSerialize()})"
    }

}