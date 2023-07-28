package org.co2dice.mirai.core.bean.effect.static_ability.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.effect.static_ability.entry.StaticAbilityEntry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-02-18:19
 * {@code @Message:} 骰子槽里可发动的静止式异能
 **/
@Serializable
class DiceSlotStaticAbilityInstance(override val entry : StaticAbilityEntry) : StaticAbilityInstance(){
}