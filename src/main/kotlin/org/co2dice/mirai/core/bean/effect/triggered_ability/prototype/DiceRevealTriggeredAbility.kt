package org.co2dice.mirai.core.bean.effect.triggered_ability.prototype

import org.co2dice.mirai.core.ast.tree.AstTree
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-07-22:45
 * @Message: 棋子中的骰子被揭示时触发的效果
 **/
class DiceRevealTriggeredAbility(
    override val uuid: UUID,
    override val launchConditions: AstTree,
    override val targetFunction: AstTree,
    override val operation: AstTree,
    override val check: AstTree,
    override val react: AstTree
) : TriggeredAbility {
}