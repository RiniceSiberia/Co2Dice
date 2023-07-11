package org.co2dice.mirai.core.ast.symbol.impl.game.situation

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.core.bean.game.zone.instance.DeskInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-20:28
 * @Message:
 **/
object GetZones : BiOpSymbol<DeskInstance, PreActivationSituation, PlayerInstance>() {
    override fun natualSign(left: INode<out PreActivationSituation>, right: INode<out PlayerInstance>): String {
        return "$left.getZones($right)"
    }

    override fun operation(l: PreActivationSituation, r: PlayerInstance, params:Params): DeskInstance {
        //空就抛出，非空就返回
        return l.scene.getDesk(r)!!
    }
}