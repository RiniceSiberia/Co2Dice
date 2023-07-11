package org.co2dice.mirai.core.bean.effect.activated_ability.instance

import org.co2dice.mirai.core.bean.api.InstanceStructure
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.ActivatedAbilityEntry


/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-19-20:26
 * @Message: Have a good time!  :)
 **/
sealed class ActivatedAbilityInstance(
    override val entry : ActivatedAbilityEntry,
): InstanceStructure<ActivatedAbilityEntry> {
}