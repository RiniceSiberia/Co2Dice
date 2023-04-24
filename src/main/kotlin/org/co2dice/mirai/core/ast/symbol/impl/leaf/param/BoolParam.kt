package org.co2dice.mirai.core.ast.symbol.impl.leaf.param

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:15
 * @Message: Have a good time!  :)
 **/
object BoolParam : ParamLeafSymbol<Boolean>(){
    init {
        SymbolRegistry.register(this)
    }

    override fun natualSign(key: String): String {
        return "$key :: Boolean"
    }

    override fun operation(key: String, params: Params): Boolean {
        return params.get(key)
    }
}