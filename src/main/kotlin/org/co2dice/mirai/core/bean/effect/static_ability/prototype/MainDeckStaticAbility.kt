package org.co2dice.mirai.core.bean.effect.static_ability.prototype

import org.co2dice.mirai.core.ast.tree.AstTree
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-23-22:52
 * @Message: Have a good time!  :)
 **/
class MainDeckStaticAbility(
    override val uuid: UUID, override val trigger : AstTree, override val operation : AstTree) : StaticAbility {
}