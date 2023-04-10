package org.co2dice.mirai.ast.symbol.impl.leaf.param

import org.co2dice.mirai.ast.Params
import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.symbol.basic.ParamLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:15
 * @Message: Have a good time!  :)
 **/
object IntegerParam : ParamLeafSymbol<Int>(){
    init {
        SymbolRegistry.register(this)
    }
    override val natualSign: String = "Int"
}