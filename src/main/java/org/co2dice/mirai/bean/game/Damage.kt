package org.co2dice.mirai.bean.game

import org.co2dice.mirai.bean.card.instance.CardInstance
import org.co2dice.mirai.bean.attribute.prototype.AttributeAPI
import org.co2dice.mirai.bean.dice.diceList.DiceList

data class Damage(
    val damager: CardInstance?,
    //伤害源
    val target: CardInstance,
    //目标
    val damage: DiceList,
    //伤害
    val damageType: AttributeAPI,
    //伤害类型
    val sourceType: List<DamageType>) {
    enum class DamageType {
        BLUDGEON,CLEAVE,PUNCTURE,
        FLYING,
        TRUE,PHYSICAL,MAGICAL,SPIRIT,
        FIRE, ICE, POISON,ELECTRIC
    }
    //伤害词缀，如钝击，切割，穿刺，飞行，真实，物理，精神，魔法，火，冰，毒,电



}