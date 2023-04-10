package org.co2dice.mirai.ast.symbol.impl.leaf.param

import org.co2dice.mirai.ast.Params
import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.symbol.basic.ParamLeafSymbol
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-22:45
 * @Message: Have a good time!  :)
 **/
object ChessmanInstanceParam : ParamLeafSymbol<ChessmanInstance>(){
    init {
        SymbolRegistry.register(this)
    }
    override val natualSign: String = "Chess"
}