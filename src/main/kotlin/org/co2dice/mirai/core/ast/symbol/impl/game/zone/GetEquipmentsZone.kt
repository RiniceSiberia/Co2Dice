package org.co2dice.mirai.core.ast.symbol.impl.game.zone

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
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
    init {
        SymbolRegistry.register(this)
    }

    override fun natualSign(left: INode<ZoneInstanceSet>, right: INode<ChessmanInstance>): String {
        return "$left.getEquipmentsZone($right)"
    }

    override fun operation(l: ZoneInstanceSet, r: ChessmanInstance): Equipments {
        //空就抛出，非空就返回
        return l.equipmentZone[r]!!
    }
}