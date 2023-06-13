package org.co2dice.mirai.core.ast.symbol.impl.game.zone

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.TriOpSymbol
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.game.zone.CardsVessel
import org.co2dice.mirai.core.bean.game.zone.ZoneInstanceSet

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-11-21:49
 * @Message: Have a good time!  :)
 **/
object MoveCard : TriOpSymbol<Boolean,ZoneInstanceSet,CardsVessel<*,*>,CardInstance<*>>() {
    override fun natualSign(
        first: INode<ZoneInstanceSet>,
        second: INode<CardsVessel<*, *>>,
        third: INode<CardInstance<*>>
    ): String {
        return "${first.natualSerialize()}.moveCard(${second.natualSerialize()},${third.natualSerialize()})"
    }

    override fun operation(f: ZoneInstanceSet, s: CardsVessel<*,*>, t: CardInstance<*>, params: Params): Boolean {
        return f.moveCard(s,t)
    }
}