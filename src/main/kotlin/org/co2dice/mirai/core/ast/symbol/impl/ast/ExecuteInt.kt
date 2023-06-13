package org.co2dice.mirai.core.ast.symbol.impl.ast

import org.co2dice.mirai.core.ast.symbol.basic.sub.ExecuteSymbol
import org.co2dice.mirai.core.ast.tree.AstTree

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-07-23:28
 * @Message: Have a good time!  :)
 **/
object ExecuteInt : ExecuteSymbol<Int>() {
    override fun natualSign(function: AstTree): String {
        return "{${function.natualSerialize()}}.execute<Intelligence>()"
    }
}