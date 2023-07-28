package org.co2dice.mirai.core.bean.chessman.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.api.InstanceStructure
import org.co2dice.mirai.core.bean.attribute.table.AttributeInstanceTable
import org.co2dice.mirai.core.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

@Serializable
sealed class ChessmanInstance() : InstanceStructure<ChessmanEntry>,CAO {
    abstract var holder: PlayerInstance
    open val attributeTable : AttributeInstanceTable = entry.attributeEntry.toInstance()
    override val chaos: Int? = entry.chaos
    override val order: Int? = entry.order
    var isAlive = true

    var isActive = false
}