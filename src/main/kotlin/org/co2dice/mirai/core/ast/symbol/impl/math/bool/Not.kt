package org.co2dice.mirai.core.ast.symbol.impl.math.bool

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol

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
    override fun operation(item: Boolean): Boolean {
        return !item
    }

    override fun natualSign(input: INode<Boolean>): String {
        return "!${input.natualSerialize()}"
    }
}