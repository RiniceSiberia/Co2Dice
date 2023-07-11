package org.co2dice.mirai.core.bean.effect.module.cost

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-22-23:39
 * @Message: 丢卡，check会返回丢卡的所有可能性组合
 **/
class DiscardCost(
    val getCard :AstTree
) : MultipleSelectionCost<Set<CardInstance>> {
    override fun check(situation: PreActivationSituation): List<Set<CardInstance>> {
        val params = Params(mutableMapOf(),situation)
        return getCard.execute<List<Set<CardInstance>>>(params) ?: emptyList()
    }

    override fun execute(obj : Set<CardInstance>, situation: ActivationSituation): Boolean {
        return situation.getHand().discard(obj)
    }
}