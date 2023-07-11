package org.co2dice.mirai.core.bean.card.prototype.api

import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.ActivatedAbility
import org.co2dice.mirai.core.bean.effect.static_ability.prototype.StaticAbility
import org.co2dice.mirai.core.bean.effect.triggered_ability.prototype.TriggeredAbility

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-02-20:00
 * @Message: 原型里永久物的api
 **/
interface PermanentPrototypeApi {
    val activatedAbilities : List<ActivatedAbility>
    val staticAbilities : List<StaticAbility>
    val triggeredAbilities :List<TriggeredAbility>
}