package org.co2dice.mirai.core.bean.effect.activated_ability.instance

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.core.bean.api.InstanceStructure
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.ActivatedAbilityEntry
import org.co2dice.mirai.core.utils.situation.ActivationSituation
import org.co2dice.mirai.core.utils.situation.PreActivationSituation


/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-19-20:26
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable
sealed class ActivatedAbilityInstance(): InstanceStructure<ActivatedAbilityEntry> {
    fun check(situation:PreActivationSituation) : Boolean {
        //发动前的遍历检查
        return entry.costCheck(situation) && entry.launchConditions(situation) && entry.targetCheck(situation)
    }


    fun getScope(situation:ActivationSituation) : JsonObject{
        //遍历获取可行的操作
        return JsonObject(mapOf(
            "cost" to entry.costGetSelectScope(situation),
            "target" to entry.targetGetSelectScope(situation),
        ))
    }

    fun


}