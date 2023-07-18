package org.co2dice.mirai.core.ast.symbol.impl.game.zone

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.game.zone.api.CardVesselApi

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-21-16:32
 * {@code @Message:} Have a good time!  :)
 **/
object ThisCardInZone : BiOpSymbol<List<CardInstance>,CardInstance,CardVesselApi<*>>() {
    override fun natualSign(left: INode<out CardInstance>, right: INode<out CardVesselApi<*>>): String {
        return "${left.natualSerialize()}.inZone(${right.natualSerialize()})"
    }

    override fun operation(l: CardInstance, r: CardVesselApi<*>, params: Params): List<CardInstance> {
        return if (r.contain(l)){
            listOf(l)
        }else{
            listOf()
        }
    }
}