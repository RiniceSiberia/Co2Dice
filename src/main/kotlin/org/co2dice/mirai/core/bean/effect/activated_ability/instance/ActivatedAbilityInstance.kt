package org.co2dice.mirai.core.bean.effect.activated_ability.instance

import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.core.ast.Params
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
sealed class ActivatedAbilityInstance(
    override val entry : ActivatedAbilityEntry,
): InstanceStructure<ActivatedAbilityEntry> {

    fun could(situation:PreActivationSituation) : Boolean {
        //发动前的遍历检查
        val t = entry.prototype
        return t.launchConditions.execute<Boolean>(Params(situation = situation)) == true
            && t.cost.check(situation)
            && t.target.check(situation)
    }


    fun can(situation:PreActivationSituation) : JsonObject{
        //返回一堆打包了的需要选择的东西
        return JsonObject(mapOf(
            "cost" to entry.prototype.cost.getSelectScope(situation),
            "target" to entry.prototype.target.getSelectScope(situation)
        ))
    }

    fun active(situation : ActivationSituation) : Boolean{
        //发动
        val t = entry.prototype
        if (t.launchConditions.execute<Boolean>(Params(situation = situation)) == true
            && t.cost.practice(situation)
            && t.target.getSelectScope(situation)
            )




    }


}