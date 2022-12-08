package org.co2dice.mirai.bean.battle

import net.mamoe.mirai.contact.Group

object BattlesPool {
    private val battles = HashMap<Long, Battle>()

    fun createBattle(group: Group) {
        battles[group.id] = Battle()
    }

    fun getBattle(group:Group) : Battle? {
        return battles[group.id]
    }
    fun finishBattle(group: Group){
        battles.remove(group.id)
    }

}