package org.co2dice.mirai.ast.symbol.impl.math.integer

import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.symbol.basic.UniOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:57
 * @Message: Have a good time!  :)
 **/
object Abs : UniOpSymbol<Int, Int>(){
    init {
        SymbolRegistry.register(this)
    }
    override val natualSign: String = "abs"
    override fun operation(item: Int): Int {
        return kotlin.math.abs(item)
    }
}