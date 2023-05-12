package org.co2dice.mirai.core.ast.symbol.impl.game.situation

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
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
    init {
        SymbolRegistry.register(this)
    }

    override fun natualSign(input: INode<PreActivationSituation>): String {
        return "$input.getPlayer()"
    }

    override fun operation(input: PreActivationSituation): PlayerInstance {
        return input.player
    }
}