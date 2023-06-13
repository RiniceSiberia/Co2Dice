package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.ListOpSymbol
import org.co2dice.mirai.core.bean.dice.entry.IntSampleSpace

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-10-18:00
 * @Message: 将骰子捻成一个组
 **/
object DiceSumSymbol : ListOpSymbol<Int, IntSampleSpace>() {

    override fun natualSign(list: List<INode<out IntSampleSpace>>): String {
        return "Sum[${list.joinToString("+") { it.natualSerialize() }}]"
    }


    override fun operation(list: List<IntSampleSpace>, params: Params): Int {
        return list.stream().mapToInt { it.roll() }.sum()
    }

}