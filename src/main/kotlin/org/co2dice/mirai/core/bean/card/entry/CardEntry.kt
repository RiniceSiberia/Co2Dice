package org.co2dice.mirai.core.bean.card.entry


import org.co2dice.mirai.core.bean.card.instance.UnPublicCardInstance
import org.co2dice.mirai.core.bean.card.prototype.*
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.instance.release.ReleaseEffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.release.ReleaseEffect
import org.co2dice.mirai.core.bean.effect.prototype.toEntry
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

class CardEntry<out C : Card>(
    val card: C,
    val cardAlias: String = card.cardRealName,
    val flavorText : String,
    val imgUrl : String,
    val effectEntry : List<EffectEntry<*>> = card.effects.stream(). map { EffectEntry(it,1)}.toList()
){



    fun initializeDeckInstance(registry : UniqueIdRegistry,holder : PlayerInstance) : UnPublicCardInstance? {
        return when(card) {
            is ItemCard -> UnPublicCardInstance(this,registry,holder,
                ReleaseEffectInstance(
                    card.effects.filterIsInstance(ReleaseEffect::class.java).map { it.toEntry() }
                )
            )
            is SkillCard -> UnPublicCardInstance(this,registry,holder,
                ReleaseEffectInstance(
                    card.effects.filterIsInstance(ReleaseEffect::class.java).map { it.toEntry() }
                )
            )
            else -> return null
        }
    }

    fun initializeVenueInstance(registry : UniqueIdRegistry,holder : PlayerInstance) : UnPublicCardInstance? {
        return when(card) {
            is VenueCard -> UnPublicCardInstance(this,registry,holder,
                ReleaseEffectInstance(
                    card.effects.filterIsInstance(ReleaseEffect::class.java).map { it.toEntry() }
                )
            )
            else -> return null
        }
    }


}