package org.co2dice.mirai.core.bean.effect.activated_ability.prototype

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.effect.module.cost.CostPackage
import org.co2dice.mirai.core.bean.effect.module.target.TargetSelectorPackage
import org.co2dice.mirai.core.utils.serializer.UUIDSerializer
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-18-20:46
 * {@code @Message:} 启动式墓地异能
 **/
@Serializable
@SerialName("gy_activated_ability")
class GYActivatedAbility(
    @Serializable(with = UUIDSerializer::class)
    override val uuid: UUID,
    override val target: TargetSelectorPackage,
    override val cost: CostPackage,
    override val operation: AstTree,
    override val check: AstTree,
    override val react: AstTree,
    override val launchConditions: AstTree
) : ActivatedAbility() {
}