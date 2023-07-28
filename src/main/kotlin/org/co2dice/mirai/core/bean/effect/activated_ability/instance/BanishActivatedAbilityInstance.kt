package org.co2dice.mirai.core.bean.effect.activated_ability.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.ActivatedAbilityEntry


/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-27-22:48
 * {@code @Message:} 除外区发动的效果
 **/
@Serializable
class BanishActivatedAbilityInstance(
    override val entry : ActivatedAbilityEntry,
) : ActivatedAbilityInstance(){
}