package org.co2dice.mirai.bean.game

import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.dice.DiceList
import org.co2dice.mirai.bean.tokens.Token

class Damage(
    val damager: Cards?,
    val target: Cards,
    val damage: DiceList,
    val damageType: Token,
    val sourceType: List<DamageType>) {
    enum class DamageType {
        BLUDGEON,CLEAVE,PUNCTURE,
        FLYING,
        TRUE,PHYSICAL,MAGICAL,SPIRIT,
        FIRE, ICE, POISON,
    }



}