package org.co2dice.mirai.core.bean.effect.instance.release

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.field.FieldEffect
import org.co2dice.mirai.core.bean.effect.prototype.release.UnPublicEffect
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-17-18:07
 * @Message: Have a good time!  :)
 **/
class UnPublicEffectInstance (
    entries : List<EffectEntry<UnPublicEffect>>
) : EffectInstance<UnPublicEffect>(entries) {

    constructor(entry : CardEntry<*>) : this(
        entries = entry.effectEntry.filterIsInstance<EffectEntry<UnPublicEffect>>()
    )
    override fun preActivationCheck(situation: PreActivationSituation): List<Int> {
        TODO("Not yet implemented")
    }

    override fun invoke(index: Int, situation: Situation, input: Map<String, Any>): String? {
        TODO("Not yet implemented")
    }
}