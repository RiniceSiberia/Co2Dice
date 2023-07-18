package org.co2dice.mirai.core.bean.effect.static_ability.prototype

import org.co2dice.mirai.core.ast.tree.AstTree
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-23-22:51
 * {@code @Message:} Have a good time!  :)
 **/
class GyStaticAbility(
    override val uuid: UUID, override val trigger : AstTree, override val operation : AstTree) : StaticAbility {
}