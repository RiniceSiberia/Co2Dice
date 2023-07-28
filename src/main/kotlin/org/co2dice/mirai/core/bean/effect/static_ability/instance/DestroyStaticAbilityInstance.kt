package org.co2dice.mirai.core.bean.effect.static_ability.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.effect.static_ability.entry.StaticAbilityEntry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-23-23:16
 * {@code @Message:} 亡语被动
 **/
@Serializable
class DestroyStaticAbilityInstance (override val entry : StaticAbilityEntry) : StaticAbilityInstance(){
}