package org.co2dice.mirai.ast.symbol.impl.math.bool

import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.symbol.basic.UniOpSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:54
 * @Message: Have a good time!  :)
 **/
object Not : UniOpSymbol<Boolean, Boolean>(){
    init {
        SymbolRegistry.register(this)
    }
    override val natualSign: String = "!"
    override fun operation(item: Boolean): Boolean {
        return !item
    }
}