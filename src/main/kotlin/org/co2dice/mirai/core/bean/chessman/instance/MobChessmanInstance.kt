package org.co2dice.mirai.core.bean.chessman.instance

import org.co2dice.mirai.core.bean.attribute.table.AttributeInstanceTable
import org.co2dice.mirai.core.bean.attribute.prototype.MobAttribute
import org.co2dice.mirai.core.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.core.bean.game.Damage
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-16:53
 * @Message: Have a good time!  :)
 **/
class MobChessmanInstance(entry : ChessmanEntry,
                          holder : PlayerInstance?,
                          attributeInstanceTable : AttributeInstanceTable = entry.chessman.attributeEntryTable.toInstance())
    : ChessmanInstance(entry = entry,holder = holder,attributeTable = attributeInstanceTable) {


    override fun makeDamage(damage: Damage): Boolean {
        attributeTable.subtractionValue(damage.damage.roll().open(), MobAttribute.LOYALTY)
        return true
    }
    //无论输入是啥，都打忠诚度
}