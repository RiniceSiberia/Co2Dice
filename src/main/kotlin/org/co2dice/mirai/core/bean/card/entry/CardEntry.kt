package org.co2dice.mirai.core.bean.card.entry


import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.EntryStructure
import org.co2dice.mirai.core.bean.card.instance.MainDeckCardInstance
import org.co2dice.mirai.core.bean.card.instance.SideDeckCardInstance
import org.co2dice.mirai.core.bean.card.prototype.Card
import org.co2dice.mirai.core.bean.card.prototype.ItemCard
import org.co2dice.mirai.core.bean.card.prototype.SkillCard
import org.co2dice.mirai.core.bean.card.prototype.VenueCard
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.ActivatedAbilityEntry
import org.co2dice.mirai.core.bean.effect.static_ability.entry.StaticAbilityEntry
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.TriggeredAbilityEntry
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.serializer.UUIDSerializer
import java.util.*
@Serializable
class CardEntry(
    @Serializable(with = UUIDSerializer::class)
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

    fun initializeDeckInstance(holder : PlayerInstance) : MainDeckCardInstance? {
        return when(prototype) {
            is ItemCard -> MainDeckCardInstance(this,holder)
            is SkillCard -> MainDeckCardInstance(this,holder)
            else -> return null
        }
    }

    fun initializeVenueInstance(holder : PlayerInstance) : SideDeckCardInstance? {
        return when(prototype) {
            is VenueCard -> SideDeckCardInstance(
                    this,holder
                )
            else -> return null
        }
    }


}