package org.co2dice.mirai.core.bean.card.entry


import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.card.instance.event.EventCardInstance
import org.co2dice.mirai.core.bean.card.instance.item.ItemCardInstance
import org.co2dice.mirai.core.bean.card.instance.skill.SkillCardInstance
import org.co2dice.mirai.core.bean.card.instance.unpublic.UnPublicCardInstance
import org.co2dice.mirai.core.bean.card.prototype.*
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.instance.release.UnPublicEffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.Effect
import org.co2dice.mirai.core.bean.effect.prototype.release.UnPublicEffect
import org.co2dice.mirai.core.bean.effect.prototype.toEntry
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

class CardEntry<C : Card>(
    val card: C,
    val cardAlias: String = card.cardRealName,
    val flavorText : String,
    val imgUrl : String,
    val effectEntry : List<EffectEntry<*>> = card.effects.stream(). map { EffectEntry(it,1)}.toList()
){



    fun initializeDeckInstance(registry : UniqueIdRegistry,holder : PlayerInstance) : CardInstance<UnPublicEffect>? {
        return when(card) {
            is ItemCard -> UnPublicCardInstance(this,registry,holder,
                UnPublicEffectInstance(
                    card.effects.filterIsInstance(UnPublicEffect::class.java).map { it.toEntry() }
                )
            )
            is SkillCard -> UnPublicCardInstance(this,registry,holder,
                UnPublicEffectInstance(
                    card.effects.filterIsInstance(UnPublicEffect::class.java).map { it.toEntry() }
                )
            )
            else -> return null
        }
    }


}