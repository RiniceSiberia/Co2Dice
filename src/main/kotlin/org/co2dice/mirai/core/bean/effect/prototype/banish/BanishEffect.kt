package org.co2dice.mirai.core.bean.effect.prototype.banish

import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.effect.EffectTargets
import org.co2dice.mirai.core.bean.effect.cost.Cost
import org.co2dice.mirai.core.bean.effect.prototype.Effect

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-26-23:12
 * @Message: Have a good time!  :)
 **/
class BanishEffect(
    override val targetFunction: AstTree<EffectTargets>,
    override val cost: AstTree<Cost>,
    override val operation: AstTree<String>,
    override val check: AstTree<Int>,
    override val react: AstTree<Int>,
    override val launchConditions: AstTree<Boolean>
) : Effect {
}