package org.co2dice.mirai.core.ast.symbol.impl.game.situation

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.effect.utils.Situation
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-20:14
 * @Message: Have a good time!  :)
 **/
object GetPlayer : UniOpSymbol<PlayerInstance, Situation>() {
    init {
        SymbolRegistry.register(this)
    }

    override fun natualSign(input: INode<Situation>): String {
        return "$input.getPlayer()"
    }

    override fun operation(input: Situation): PlayerInstance {
        return input.player
    }
}