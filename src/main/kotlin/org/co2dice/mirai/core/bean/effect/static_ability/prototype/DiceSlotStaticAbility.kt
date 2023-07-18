package org.co2dice.mirai.core.bean.effect.static_ability.prototype

import org.co2dice.mirai.core.ast.tree.AstTree
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-02-18:18
 * {@code @Message:} 专属于棋子的静止式效果
 **/
class DiceSlotStaticAbility(
    override val uuid: UUID,
    override val trigger: AstTree,
    override val operation: AstTree) : StaticAbility{
}