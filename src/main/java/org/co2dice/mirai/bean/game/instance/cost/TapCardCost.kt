package org.co2dice.mirai.bean.game.instance.cost

import org.co2dice.mirai.bean.game.instance.card.Situation

class TapCardCost(var num:Int) : AbstractCost() {
    // 横置场上n张卡，但是筛选器的条件作用于所有目标。
    // 可以做到的场景举例：横置场上的所有力量小于2的怪兽
    // 不可以做到的场景举例：横置场上的1只力量小于1的怪兽和1只力量大于1的怪兽

    override fun check(situation: Situation): Boolean {
        val c = situation.chessman

    }

}