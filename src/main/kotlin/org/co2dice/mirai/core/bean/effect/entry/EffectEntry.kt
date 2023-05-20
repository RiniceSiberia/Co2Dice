package org.co2dice.mirai.core.bean.effect.entry

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.instance.field.FieldActiveEffectInstance
import org.co2dice.mirai.core.bean.effect.instance.release.UnPublicEffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.Effect
import org.co2dice.mirai.core.bean.effect.prototype.field.FieldEffect
import org.co2dice.mirai.core.bean.effect.prototype.release.UnPublicEffect

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