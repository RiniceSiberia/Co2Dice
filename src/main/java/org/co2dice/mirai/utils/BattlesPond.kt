package org.co2dice.mirai.utils

import net.mamoe.mirai.contact.Group

object BattlesPond {
    private val battlePond = HashMap<Long, Battle>()

    fun createBattle(group: Group) {
        val battle = Battle()
        this.battlePond[group.id] = battle
    }

    fun getBattle(group:Group) : Battle? {
        return this.battlePond[group.id]
    }
    fun finishBattle(group: Group){
        this.battlePond.remove(group.id)
    }

}