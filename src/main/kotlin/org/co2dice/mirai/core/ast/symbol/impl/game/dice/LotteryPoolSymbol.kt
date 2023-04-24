package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.ListOpSymbol
import org.co2dice.mirai.core.bean.dice.single.LotteryPool

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-12-17:55
 * @Message: Have a good time!  :)
 **/
object LotteryPoolSymbol : ListOpSymbol<LotteryPool, Int>() {
    init {
        SymbolRegistry.register(this)
    }

    override fun operation(list: List<Int>): LotteryPool {
        return LotteryPool(list)
    }

    override fun natualSign(list: List<INode<out Int>>): String {
        return "lottery[${list.joinToString(","){ it.natualSerialize() }}]"
    }
}