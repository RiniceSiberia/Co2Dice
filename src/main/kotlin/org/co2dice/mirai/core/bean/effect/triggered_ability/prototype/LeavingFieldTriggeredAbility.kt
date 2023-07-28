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
 * {@code @Time:}  2023-06-28-22:43
 * {@code @Message:} 离开战场时触发的效果
 **/
@Serializable
@SerialName("leaving_field_triggered_ability")
class LeavingFieldTriggeredAbility (
    @Serializable(with = UUIDSerializer::class)
    override val uuid: UUID,
    override val launchConditions: AstTree,
    //触发条件,false则不触发,如果是技能就是无法发动，道具或者事件就是无事发生
    override val target: TargetSelectorPackage,
    //效果对象，必须在进场前确定好，否则无法下场(类似炉石)。注:如果触发条件不满足，则无需选择。
    //亡语没有cost，跳过
    override val operation: AstTree,
    //具体效果
    override val check: AstTree,
    //检定
    override val react: AstTree
    //对抗
) : TriggeredAbility(){
}