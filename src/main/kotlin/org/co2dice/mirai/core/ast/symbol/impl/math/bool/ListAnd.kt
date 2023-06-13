package org.co2dice.mirai.core.ast.symbol.impl.math.bool

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.ListOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-31-17:56
 * @Message: Have a good time!  :)
 **/
object ListAnd : ListOpSymbol<Boolean, Boolean>(){
    override fun natualSign(list: List<INode<out Boolean>>): String {
        return list.joinToString(" && ", "(", ")") { it.natualSerialize() }
    }

    override fun check(list: List<Boolean>, params:Params): Boolean {
        return list.all { it }
    }


}