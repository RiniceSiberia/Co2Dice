package org.co2dice.mirai.core.bean.api

import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.ActiveEffect
import org.co2dice.mirai.core.bean.effect.prototype.Effect
import org.co2dice.mirai.core.bean.effect.prototype.PassiveEffect

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-20-13:29
 * @Message: 存储特殊效果的接口
 **/
interface Agent<E : Effect> {
    var effects : EffectInstance<E>
}