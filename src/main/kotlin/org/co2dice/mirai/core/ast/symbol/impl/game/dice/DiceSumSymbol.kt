package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.ListOpSymbol
import org.co2dice.mirai.core.bean.dice.roll

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-10-18:00
 * {@code @Message:} 将骰子捻成一个组
 **/
object DiceSumSymbol : ListOpSymbol<Int, HashMap<Int,Int>>() {

    override fun natualSign(list: List<INode<out HashMap<Int,Int>>>): String {
        return "Sum[${list.joinToString("+") { it.natualSerialize() }}]"
    }


    override fun operation(list: List<HashMap<Int,Int>>, params: Params): Int {
        return list.stream().mapToInt { it.roll() }.sum()
    }

}