package org.co2dice.mirai.core.ast.symbol.impl.game.zone

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.game.zone.instance.HandInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-17-20:52
 * @Message: Have a good time!  :)
 **/
object HandCombinationRestrictAmountSymbol : BiOpSymbol<List<Set<CardInstance>>, HandInstance,Int>() {
    override fun natualSign(left: INode<out HandInstance>, right: INode<out Int>): String {
        return "${left.natualSerialize()}.combinationRestrictAmount(${right.natualSerialize()})"
    }

    override fun operation(l: HandInstance, r: Int, params: Params): List<Set<CardInstance>> {
        return l.getCombinationsHands({ it.size == r })
    }


}