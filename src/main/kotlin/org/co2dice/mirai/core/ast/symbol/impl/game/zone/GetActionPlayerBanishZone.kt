package org.co2dice.mirai.core.ast.symbol.impl.game.zone

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.game.zone.instance.BanishZoneInstance
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-20-22:57
 * @Message: Have a good time!  :)
 **/
object GetActionPlayerBanishZone: UniOpSymbol<BanishZoneInstance, PreActivationSituation>() {


    override fun natualSign(input: INode<PreActivationSituation>): String {
        return "${input.natualSerialize()}.getActionPlayerBanishZone()"
    }

    override fun operation(input: PreActivationSituation, params: Params): BanishZoneInstance {
        return input.getBanish()
    }
}