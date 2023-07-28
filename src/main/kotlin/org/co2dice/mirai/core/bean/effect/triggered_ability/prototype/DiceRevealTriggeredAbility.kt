package org.co2dice.mirai.core.bean.effect.triggered_ability.prototype

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.effect.module.target.TargetSelectorPackage
import org.co2dice.mirai.core.utils.serializer.UUIDSerializer
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-07-22:45
 * {@code @Message:} 棋子中的骰子被揭示时触发的效果
 **/
@Serializable
@SerialName("dice_reveal_triggered_ability")
class DiceRevealTriggeredAbility(
    @Serializable(with = UUIDSerializer::class)
    override val uuid: UUID,
    override val launchConditions: AstTree,
    override val target: TargetSelectorPackage,
    override val operation: AstTree,
    override val check: AstTree,
    override val react: AstTree
) : TriggeredAbility() {
}