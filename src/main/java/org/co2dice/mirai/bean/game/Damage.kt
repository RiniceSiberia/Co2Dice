package org.co2dice.mirai.bean.game

import org.co2dice.mirai.bean.card.instance.CardInstance
import org.co2dice.mirai.bean.dice.DiceList
import org.co2dice.mirai.bean.counter.Counter

class Damage(
    val damager: CardInstance?,
    val target: CardInstance,
    val damage: DiceList,
    val damageType: Counter,
    val sourceType: List<DamageType>) {
    enum class DamageType {
        BLUDGEON,CLEAVE,PUNCTURE,
        FLYING,
        TRUE,PHYSICAL,MAGICAL,SPIRIT,
        FIRE, ICE, POISON,
    }



}