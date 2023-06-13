package org.co2dice.mirai.core.bean.effect.instance.gy

import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.gy.GYEffect
import org.co2dice.mirai.core.utils.situation.PreActivationSituation
import org.co2dice.mirai.core.utils.situation.SituationApi

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-27-22:44
 * @Message: Have a good time!  :)
 **/
class GYEffectInstance(
entries : List<EffectEntry<GYEffect>>
) : EffectInstance<GYEffect>(entries){
    override fun preActivationCheck(situation: PreActivationSituation): List<Int> {
        TODO("Not yet implemented")
    }

    override fun invoke(index: Int, situation: SituationApi, input: Map<String, Any>): String? {
        TODO("Not yet implemented")
    }
}