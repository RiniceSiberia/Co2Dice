package org.co2dice.mirai.core.ast.symbol.impl.game.agent

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.utils.situation.PreActivationSituation
import org.co2dice.mirai.core.utils.situation.getAgentCardInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-21-16:29
 * @Message: Have a good time!  :)
 **/
object AgentToCardInstanceAny : UniOpSymbol<CardInstance<*>,PreActivationSituation>() {
    override fun natualSign(input: INode<out PreActivationSituation>): String {
        return "${input.natualSerialize()}.getAgentToCardInstance<*>()"
    }

    override fun operation(input: PreActivationSituation, params: Params): CardInstance<*> {
        return input.getAgentCardInstance<CardInstance<*>>()!!
    }
}