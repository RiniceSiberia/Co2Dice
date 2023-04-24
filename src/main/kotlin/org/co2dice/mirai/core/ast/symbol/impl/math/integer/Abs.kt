package org.co2dice.mirai.core.ast.symbol.impl.math.integer

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol

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
    override fun operation(item: Int): Int {
        return kotlin.math.abs(item)
    }

    override fun natualSign(input: INode<Int>): String {
        return "|${input.natualSerialize()}|"
    }

}