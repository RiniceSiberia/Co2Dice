package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.ListOpSymbol
import org.co2dice.mirai.core.bean.dice.entry.LotteryPool

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-12-17:55
 * {@code @Message:} Have a good time!  :)
 **/
object LotteryPoolSymbol : ListOpSymbol<LotteryPool, Int>() {
    override fun operation(list: List<Int>, params:Params): LotteryPool {
        return LotteryPool(list)
    }

    override fun natualSign(list: List<INode<out Int>>): String {
        return "lottery[${list.joinToString(","){ it.natualSerialize() }}]"
    }
}