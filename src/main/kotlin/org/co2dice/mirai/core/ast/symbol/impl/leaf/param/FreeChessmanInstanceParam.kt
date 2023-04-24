package org.co2dice.mirai.core.ast.symbol.impl.leaf.param

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-22:45
 * @Message: Have a good time!  :)
 **/
object FreeChessmanInstanceParam : ParamLeafSymbol<ChessmanInstance>(){
    init {
        SymbolRegistry.register(this)
    }

    override fun natualSign(key: String): String {
        return "$key :: ChessmanInstance(Free)"
    }

    override fun operation(key: String, params: Params): ChessmanInstance {
        return params.get(key)
    }
}