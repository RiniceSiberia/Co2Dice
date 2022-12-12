package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.battle.Battle
import org.co2dice.mirai.bean.cards.Cards

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:39
 * @Message: Have a good time!  :)
 **/
class SkillActiveEffects {

    fun blunt(input:MutableList<String>,battle: Battle,skill:SkillActive,target:Cards):CAOPot{
        //造成伤害，取伤害数值
        var caoPot = CAOPot(0,0)
//        var skillHolder = skill.holder
//        var holderCharacter:CharacterCard? = skill.getHolder()
//        val level = skill.level
//
//
//        var diceList =
//        var damage:Damage = Damage( holderCharacter, target, damageType = Damage.DamageType.BLUDGEON)
        return caoPot
    }
}