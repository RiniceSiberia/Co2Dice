package org.co2dice.mirai.core.bean.effect.static_ability.prototype

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.api.PrototypeStructure

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-22-23:42
 * {@code @Message:} 永续型的效果，和主动发动的Effect区分开
 **/
@Serializable
sealed class StaticAbility : PrototypeStructure{

    abstract val trigger : AstTree
    //触发的条件,如:当附着的对象HP为0时/当附着的对象受到伤害时发动
    abstract val operation : AstTree
    //具体触发的效果
}