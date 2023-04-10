package org.co2dice.mirai.ast.symbol.impl.math.bool

import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.symbol.basic.BiOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:39
 * @Message: Have a good time!  :)
 **/
object Less : BiOpSymbol<Boolean, Int, Int>(){
    init {
        SymbolRegistry.register(this)
    }
    override val natualSign: String = "<"
    override fun operation(l: Int, r: Int): Boolean {
        return l < r
    }
}