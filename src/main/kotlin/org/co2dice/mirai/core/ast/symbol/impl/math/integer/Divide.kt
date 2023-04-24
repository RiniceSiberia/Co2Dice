package org.co2dice.mirai.core.ast.symbol.impl.math.integer

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol

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
    override fun operation(l: Int, r: Int): Int {
        return l / r
    }

    override fun natualSign(left: INode<Int>, right: INode<Int>): String {
        return "(${left.natualSerialize()} / ${right.natualSerialize()})"
    }

}