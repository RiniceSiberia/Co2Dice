package org.co2dice.mirai.core.ast.symbol.impl.game.zone

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.TriOpSymbol
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.game.zone.instance.CardListVessel
import org.co2dice.mirai.core.bean.game.zone.instance.Scene

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-11-21:49
 * {@code @Message:} Have a good time!  :)
 **/
object MoveCard : TriOpSymbol<Boolean, Scene, CardListVessel<*>,CardInstance>() {
    override fun natualSign(
        first: INode<out Scene>,
        second: INode<out CardListVessel<*>>,
        third: INode<out CardInstance>
    ): String {
        return "${first.natualSerialize()}.moveCard(${second.natualSerialize()},${third.natualSerialize()})"
    }

    override fun operation(f: Scene, s: CardListVessel<*>, t: CardInstance, params: Params): Boolean {
        return f.moveCard(
            card = t,
            from = f.findCardCostVessel(t)!!,
            to = s,)
    }
}