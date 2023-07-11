package org.co2dice.mirai.core.bean.chessman.instance

import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.api.InstanceStructure
import org.co2dice.mirai.core.bean.attribute.table.AttributeInstanceTable
import org.co2dice.mirai.core.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

sealed class ChessmanInstance(
    override val entry : ChessmanEntry,
    var holder: PlayerInstance,
    val attributeTable : AttributeInstanceTable = entry.attributeEntry.toInstance(),
    override val chaos: Int? = entry.chaos,
    override val order: Int? = entry.order,
) : InstanceStructure<ChessmanEntry>,CAO {


    var isAlive = true

    var isActive = false
}