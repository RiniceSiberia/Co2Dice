package org.co2dice.mirai.core.bean.effect.triggered_ability.prototype

import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.effect.module.target.TargetSelectorPackage
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-07-22:45
 * {@code @Message:} 棋子中的骰子被揭示时触发的效果
 **/
class DiceRevealTriggeredAbility(
    override val uuid: UUID,
    override val launchConditions: AstTree,
    override val target: TargetSelectorPackage,
    override val operation: AstTree,
    override val check: AstTree,
    override val react: AstTree
) : TriggeredAbility {
}