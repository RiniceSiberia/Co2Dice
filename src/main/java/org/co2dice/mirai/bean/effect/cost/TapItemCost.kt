package org.co2dice.mirai.bean.effect.cost

import org.co2dice.mirai.bean.effect.utils.Situation
import org.co2dice.mirai.publicEnums.CardInstanceTempData

class TapItemCost(var num:Int) : AbstractCost() {
    // 横置场上n张道具卡，但是筛选器的条件作用于所有目标。
    // 可以做到的场景举例：横置场上的所有力量小于2的怪兽
    // 不可以做到的场景举例：横置场上的1只力量小于1的怪兽和1只力量大于1的怪兽

    override fun check(situation: Situation): Boolean {
        val zone = situation.getActionHolderZone()
        if (zone != null){
            val n = zone.equipmentZone.values.stream().map { it.getAllEquip() }
            //将其转换为一个set
                .flatMap { it.stream() }.filter{it.onFieldData[CardInstanceTempData.TAP] == true}
                .count()
            if (n > this.num ){
                return true
            }
        }
        return false
    }
}