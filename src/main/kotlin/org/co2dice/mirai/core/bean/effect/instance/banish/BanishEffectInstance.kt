package org.co2dice.mirai.core.bean.effect.instance.banish

import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.banish.BanishEffect
import org.co2dice.mirai.core.bean.effect.prototype.gy.GYEffect
import org.co2dice.mirai.core.utils.situation.PreActivationSituation
import org.co2dice.mirai.core.utils.situation.SituationApi

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-27-22:48
 * @Message: 除外区发动的效果
 **/
class BanishEffectInstance(
    entries : List<EffectEntry<BanishEffect>>
) : EffectInstance<BanishEffect>(entries){
    override fun preActivationCheck(situation: PreActivationSituation): List<Int> {
        TODO("Not yet implemented")
    }

    override fun invoke(index: Int, situation: SituationApi, input: Map<String, Any>): String? {
        TODO("Not yet implemented")
    }
}