package org.co2dice.mirai.core.ast.symbol.impl.game.card

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.card.prototype.Card

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-21-17:09
 * @Message: Have a good time!  :)
 **/
object GetProtoTypeSymbol : UniOpSymbol<Card,CardInstance>(){
    override fun natualSign(input: INode<out CardInstance>): String {
        return "${input.natualSerialize()}.getProtoType()"
    }

    override fun operation(input: CardInstance, params: Params): Card {
        return input.entry.prototype
    }
}