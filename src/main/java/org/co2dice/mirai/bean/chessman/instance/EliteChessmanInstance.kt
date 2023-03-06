package org.co2dice.mirai.bean.chessman.instance

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.chessman.attribute.AttributeInstanceTable
import org.co2dice.mirai.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.bean.game.Damage

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-15:46
 * @Message: Have a good time!  :)
 **/
open class EliteChessmanInstance(
    entry: ChessmanEntry,
    holder: Player?,
    attributeInstanceTable: AttributeInstanceTable = entry.chessman.attributeEntryTable.toInstance()
)
    : ChessmanInstance(entry = entry,holder = holder,attributeInstanceTable = attributeInstanceTable) {

    override fun makeDamage(damage: Damage): Boolean {
        if (attributeInstanceTable.contain(damage.damageType)) {
            attributeInstanceTable.subtractionValue(damage.damage.roll().getResult(),damage.damageType)
            return true
        }
        return false
    }
    //有属性打对应属性，没属性返回失败
}