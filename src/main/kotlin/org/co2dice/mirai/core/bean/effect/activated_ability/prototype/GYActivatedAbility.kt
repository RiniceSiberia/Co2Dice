package org.co2dice.mirai.core.bean.effect.activated_ability.prototype

import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.effect.module.cost.CostPackage
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-18-20:46
 * @Message: Have a good time!  :)
 **/
class GYActivatedAbility(
    override val uuid: UUID,
    override val targetFunction: AstTree,
    override val cost: CostPackage,
    override val operation: AstTree,
    override val check: AstTree,
    override val react: AstTree,
    override val launchConditions: AstTree
) : ActivatedAbility {
}