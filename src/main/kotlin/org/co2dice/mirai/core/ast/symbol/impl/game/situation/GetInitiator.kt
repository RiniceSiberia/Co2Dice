package org.co2dice.mirai.core.ast.symbol.impl.game.situation

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.utils.situation.ActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-20-23:05
 * @Message: Have a good time!  :)
 **/
object GetInitiator : UniOpSymbol<ChessmanInstance, ActivationSituation>() {

    override fun natualSign(input: INode<out ActivationSituation>): String {
        return "$input.getInitiator()"
    }

    override fun operation(input: ActivationSituation, params:Params): ChessmanInstance {
        return input.initiator!!
    }
}