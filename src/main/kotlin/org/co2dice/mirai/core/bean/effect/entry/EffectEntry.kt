package org.co2dice.mirai.core.bean.effect.entry

import org.co2dice.mirai.core.bean.effect.prototype.Effect

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-19-20:20
 * @Message: Have a good time!  :)
 **/
class EffectEntry<E : Effect>(
    val effect : E,
    val level : Int = 1
    //备用的技能等级，用作测试,目前没用
)  {


}