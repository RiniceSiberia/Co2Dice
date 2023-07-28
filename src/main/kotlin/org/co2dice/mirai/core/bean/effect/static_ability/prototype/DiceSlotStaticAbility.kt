package org.co2dice.mirai.core.bean.effect.static_ability.prototype

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.utils.serializer.UUIDSerializer
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-02-18:18
 * {@code @Message:} 专属于棋子的静止式效果
 **/
@Serializable
@SerialName("dice_slot_static_ability")
class DiceSlotStaticAbility(
    @Serializable(with = UUIDSerializer::class)
    override val uuid: UUID,
    override val trigger: AstTree,
    override val operation: AstTree) : StaticAbility() {
}