package org.co2dice.mirai.ast.symbol.impl.math.integer

import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.symbol.basic.BiOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:27
 * @Message: Have a good time!  :)
 **/
object Divide : BiOpSymbol<Int, Int, Int>(){
    init {
        SymbolRegistry.register(this)
    }
    override val natualSign: String = "/"
    override fun operation(l: Int, r: Int): Int {
        return l / r
    }
}