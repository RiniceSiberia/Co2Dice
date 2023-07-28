package org.co2dice.mirai.core.bean.effect.static_ability.prototype

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.utils.serializer.UUIDSerializer
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-23-22:52
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable
@SerialName("banish_static_ability")
class BanishStaticAbility(
    @Serializable(with = UUIDSerializer::class)
    override val uuid: UUID,
    override val trigger : AstTree,
    override val operation : AstTree) : StaticAbility() {
}