package org.co2dice.mirai.core.ast.symbol.impl.game.card

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.card.instance.CardInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-21-17:11
 * @Message: Have a good time!  :)
 **/
object GetCardChaos : UniOpSymbol<Int,CardInstance>() {
    override fun natualSign(input: INode<out CardInstance>): String {
        return "${input.natualSerialize()}.getCardChaos()"
    }

    override fun operation(input: CardInstance, params: Params): Int {
        if (input is CAO && input.chaos != null)
            return input.chaos!!
        else
            throw NullPointerException("input is not CAO")
    }
}