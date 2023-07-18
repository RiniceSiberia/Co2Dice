package org.co2dice.mirai.core.bean.card.entry


import org.co2dice.mirai.core.bean.api.EntryStructure
import org.co2dice.mirai.core.bean.card.instance.MainDeckUnPublicCardInstance
import org.co2dice.mirai.core.bean.card.instance.SideDeckUnPublicCardInstance
import org.co2dice.mirai.core.bean.card.prototype.Card
import org.co2dice.mirai.core.bean.card.prototype.ItemCard
import org.co2dice.mirai.core.bean.card.prototype.SkillCard
import org.co2dice.mirai.core.bean.card.prototype.VenueCard
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.ActivatedAbilityEntry
import org.co2dice.mirai.core.bean.effect.static_ability.entry.StaticAbilityEntry
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.TriggeredAbilityEntry
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import java.util.*

class CardEntry(
    override val uuid: UUID = UUID.randomUUID(),
    override val prototype: Card,
    val cardAlias: String = prototype.cardRealName,
    val flavorText : String = "",
    val imgUrl : String = "",
    val activatedAbilityEntries : List<ActivatedAbilityEntry>
    = prototype.activatedAbilities.stream(). map { ActivatedAbilityEntry(prototype = it)}.toList(),
    val staticAbilityEntries : List<StaticAbilityEntry>
    = prototype.staticAbilities.stream(). map { StaticAbilityEntry(prototype = it) }.toList(),
    val triggeredAbilityEntries : List<TriggeredAbilityEntry>
    = prototype.triggeredAbilities.stream().map { TriggeredAbilityEntry(prototype = it) }.toList(),
) : EntryStructure<Card>{

    fun initializeDeckInstance(holder : PlayerInstance) : MainDeckUnPublicCardInstance? {
        return when(prototype) {
            is ItemCard -> MainDeckUnPublicCardInstance(this,holder)
            is SkillCard -> MainDeckUnPublicCardInstance(this,holder)
            else -> return null
        }
    }

    fun initializeVenueInstance(holder : PlayerInstance) : SideDeckUnPublicCardInstance? {
        return when(prototype) {
            is VenueCard -> SideDeckUnPublicCardInstance(
                    this,holder
                )
            else -> return null
        }
    }


}