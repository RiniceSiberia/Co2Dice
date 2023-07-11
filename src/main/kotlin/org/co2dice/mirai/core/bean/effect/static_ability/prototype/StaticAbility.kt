package org.co2dice.mirai.core.bean.effect.static_ability.prototype

import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.api.PrototypeStructure

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-22-23:42
 * @Message: 永续型的效果，和主动发动的Effect区分开
 **/
sealed interface StaticAbility : PrototypeStructure{

    val trigger : AstTree
    //触发的条件,如:当附着的对象HP为0时/当附着的对象受到伤害时发动
    val operation : AstTree
    //具体触发的效果
}