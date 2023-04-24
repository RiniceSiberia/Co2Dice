package org.co2dice.mirai.core.ast.symbol.impl.game.situation

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.core.bean.effect.utils.Situation
import org.co2dice.mirai.core.bean.game.zone.ZoneInstanceSet
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-20:28
 * @Message:
 **/
object GetZones : BiOpSymbol<ZoneInstanceSet, Situation, PlayerInstance>() {
    init {
        SymbolRegistry.register(this)
    }

    override fun natualSign(left: INode<Situation>, right: INode<PlayerInstance>): String {
        return "$left.getZones($right)"
    }

    override fun operation(l: Situation, r: PlayerInstance): ZoneInstanceSet {
        //空就抛出，非空就返回
        return l.scene.zones[r]!!
    }
}