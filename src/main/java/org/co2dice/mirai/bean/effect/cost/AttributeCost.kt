package org.co2dice.mirai.bean.effect.cost

import org.co2dice.mirai.bean.attribute.table.AttributeEntryTable
import org.co2dice.mirai.bean.effect.utils.Situation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-07-19:05
 * @Message: Have a good time!  :)
 **/
class AttributeCost(val table : AttributeEntryTable): AbstractCost() {
    override fun check(situation: Situation): Boolean {
        return true
    }
}