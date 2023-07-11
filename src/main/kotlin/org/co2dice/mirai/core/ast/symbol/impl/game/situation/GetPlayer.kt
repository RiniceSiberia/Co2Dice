package org.co2dice.mirai.core.ast.symbol.impl.game.situation

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-20:14
 * @Message: Have a good time!  :)
 **/
object GetPlayer : UniOpSymbol<PlayerInstance, PreActivationSituation>() {

    override fun natualSign(input: INode<out PreActivationSituation>): String {
        return "$input.getPlayer()"
    }

    override fun operation(input: PreActivationSituation, params:Params): PlayerInstance {
        return input.player
    }
}