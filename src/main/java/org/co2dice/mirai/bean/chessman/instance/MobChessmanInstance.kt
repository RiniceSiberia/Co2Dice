package org.co2dice.mirai.bean.chessman.instance

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.attribute.table.AttributeInstanceTable
import org.co2dice.mirai.bean.attribute.prototype.MobAttribute
import org.co2dice.mirai.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.bean.game.Damage

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-16:53
 * @Message: Have a good time!  :)
 **/
class MobChessmanInstance(entry : ChessmanEntry,
                          holder : Player?,
                          attributeInstanceTable : AttributeInstanceTable = entry.chessman.attributeEntryTable.toInstance())
    : ChessmanInstance(entry = entry,holder = holder,attributeInstanceTable = attributeInstanceTable) {


    override fun makeDamage(damage: Damage): Boolean {
        attributeInstanceTable.subtractionValue(damage.damage.roll().getResult(), MobAttribute.LOYALTY)
        return true
    }
    //无论输入是啥，都打忠诚度
}