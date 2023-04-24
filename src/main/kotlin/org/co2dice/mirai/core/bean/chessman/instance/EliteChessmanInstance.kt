package org.co2dice.mirai.core.bean.chessman.instance

import org.co2dice.mirai.core.bean.attribute.table.AttributeInstanceTable
import org.co2dice.mirai.core.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.core.bean.game.Damage
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-15:46
 * @Message: Have a good time!  :)
 **/
open class EliteChessmanInstance(
    entry: ChessmanEntry,
    holder: PlayerInstance?,
    attributeInstanceTable: AttributeInstanceTable = entry.chessman.attributeEntryTable.toInstance()
)
    : ChessmanInstance(entry = entry,holder = holder,attributeTable = attributeInstanceTable) {

    override fun makeDamage(damage: Damage): Boolean {
        if (attributeTable.contain(damage.damageType)) {
            attributeTable.subtractionValue(damage.damage.roll().open(),damage.damageType)
            return true
        }
        return false
    }
    //有属性打对应属性，没属性返回失败
}