package org.co2dice.mirai.core.ast.symbol.impl.game.situation

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.bean.effect.utils.Situation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-20-23:05
 * @Message: Have a good time!  :)
 **/
object GetInitiator : UniOpSymbol<ChessmanInstance, Situation>() {
    init {
        SymbolRegistry.register(this)
    }

    override fun natualSign(input: INode<Situation>): String {
        return "$input.getInitiator()"
    }

    override fun operation(input: Situation): ChessmanInstance {
        return input.initiator!!
    }
}