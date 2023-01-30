package org.co2dice.mirai.bean.cards.cost

import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.Situation
import org.co2dice.mirai.bean.cards.api.EffectAPI
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.game.Scene

class SetCardCos(var num:Int) : AbstractCost() {
    // 横置场上n个道具，但是筛选器的条件作用于所有目标。
    // 可以做到的场景举例：横置场上的所有力量小于2的怪兽
    // 不可以做到的场景举例：横置场上的1只力量小于1的怪兽和1只力量大于1的怪兽

    override fun check(situation: Situation): Boolean {

    }
}