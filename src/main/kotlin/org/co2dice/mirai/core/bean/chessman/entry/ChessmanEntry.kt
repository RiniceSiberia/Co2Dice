package org.co2dice.mirai.core.bean.chessman.entry

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.api.EntryStructure
import org.co2dice.mirai.core.bean.attribute.table.AttributeTable
import org.co2dice.mirai.core.bean.chessman.prototype.Chessman
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.ActivatedAbilityEntry
import org.co2dice.mirai.core.bean.effect.static_ability.entry.StaticAbilityEntry
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.TriggeredAbilityEntry
import org.co2dice.mirai.core.utils.serializer.UUIDSerializer
import java.util.*

@Serializable
class ChessmanEntry(
    @Serializable(with = UUIDSerializer::class)
    override val uuid: UUID = UUID.randomUUID(),
    override val prototype: Chessman,
    override val chaos: Int? = if (prototype is CAO && prototype.chaos != null) prototype.chaos else null,
    override val order: Int? = if (prototype is CAO && prototype.order != null) prototype.order else null,
    val dice : Map<Int,Int> = prototype.dice,
    val attributeEntry : AttributeTable = prototype.attributeTable,
    val activatedAbilityEntries : List<ActivatedAbilityEntry>
    = prototype.activatedAbilities.stream(). map { ActivatedAbilityEntry(prototype = it) }.toList(),
    val staticAbilityEntries : List<StaticAbilityEntry>
    = prototype.staticAbilities.stream(). map { StaticAbilityEntry(prototype = it) }.toList(),
    val triggeredAbilityEntries : List<TriggeredAbilityEntry>
    = prototype.triggeredAbilities.stream().map { TriggeredAbilityEntry(prototype = it) }.toList(),
    ) :EntryStructure<Chessman>, CAO {

}