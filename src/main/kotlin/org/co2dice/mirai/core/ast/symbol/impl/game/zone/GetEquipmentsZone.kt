package org.co2dice.mirai.core.ast.symbol.impl.game.zone

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.bean.game.zone.Equipments
import org.co2dice.mirai.core.bean.game.zone.ZoneInstanceSet

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-21:00
 * @Message: Have a good time!  :)
 **/
object GetEquipmentsZone : BiOpSymbol<Equipments, ZoneInstanceSet, ChessmanInstance>() {

    override fun natualSign(left: INode<ZoneInstanceSet>, right: INode<ChessmanInstance>): String {
        return "$left.getEquipmentsZone($right)"
    }

    override fun operation(l: ZoneInstanceSet, r: ChessmanInstance, params:Params): Equipments {
        //空就抛出，非空就返回
        return l.equipmentZone[r]!!
    }
}