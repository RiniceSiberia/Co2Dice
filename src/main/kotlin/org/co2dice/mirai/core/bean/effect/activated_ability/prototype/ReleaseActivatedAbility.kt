package org.co2dice.mirai.core.bean.effect.activated_ability.prototype

import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.effect.module.cost.CostPackage
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-21-17:50
 * @Message: 需要释放的效果，如法术，
 **/
class ReleaseActivatedAbility(
    override val uuid: UUID,
    override val targetFunction: AstTree,
    override val cost: CostPackage,
    override val operation: AstTree,
    override val check: AstTree,
    override val react: AstTree,
    override val launchConditions: AstTree
) : ActivatedAbility {
}