package org.co2dice.mirai.core.bean.effect.activated_ability.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.ActivatedAbilityEntry


/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-27-22:44
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable
class GyActivatedAbilityInstance(
    override val entry : ActivatedAbilityEntry,
) : ActivatedAbilityInstance(){
}