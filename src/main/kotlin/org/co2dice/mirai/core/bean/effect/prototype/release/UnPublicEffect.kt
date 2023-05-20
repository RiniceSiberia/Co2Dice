package org.co2dice.mirai.core.bean.effect.prototype.release

import org.co2dice.mirai.core.ast.AstTree
import org.co2dice.mirai.core.bean.effect.EffectTargets
import org.co2dice.mirai.core.bean.effect.cost.Costs
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.prototype.Effect
/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-21-17:50
 * @Message: 需要释放的效果，如法术，
 **/
class UnPublicEffect(
    override val targetFunction: AstTree<EffectTargets>,
    override val cost: AstTree<Costs>,
    override val launchConditions: AstTree<Boolean>,
    override val check: AstTree<Int>,
    override val react: AstTree<Int>,
    override val operation: AstTree<String>,
) : Effect {
}