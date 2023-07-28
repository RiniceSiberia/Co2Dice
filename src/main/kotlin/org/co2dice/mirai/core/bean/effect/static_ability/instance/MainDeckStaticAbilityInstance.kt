package org.co2dice.mirai.core.bean.effect.static_ability.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.effect.static_ability.entry.StaticAbilityEntry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-23-23:00
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable
class MainDeckStaticAbilityInstance(override val entry : StaticAbilityEntry) : StaticAbilityInstance() {
}