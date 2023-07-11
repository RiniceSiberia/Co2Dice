package org.co2dice.mirai.core.ast.symbol.impl.game.zone

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.game.zone.instance.GyZoneInstance
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-20-22:43
 * @Message: Have a good time!  :)
 **/
object GetActionPlayerGyZone: UniOpSymbol<GyZoneInstance, PreActivationSituation>() {


    override fun natualSign(input: INode<out PreActivationSituation>): String {
        return "${input.natualSerialize()}.getActionPlayerGYZone()"
    }

    override fun operation(input: PreActivationSituation, params: Params): GyZoneInstance {
        return input.getGy()
    }
}