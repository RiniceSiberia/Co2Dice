package org.co2dice.mirai.bean.battle

import net.mamoe.mirai.contact.Group

object BattlesPond {
    private val battlePond = HashMap<Long, Battle>()

    fun createBattle(group: Group) {
        val battle = Battle()
        battlePond[group.id] = battle
    }

    fun getBattle(group:Group) : Battle? {
        return battlePond[group.id]
    }
    fun finishBattle(group: Group){
        battlePond.remove(group.id)
    }

}