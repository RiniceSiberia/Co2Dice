package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.battle.Battle
import org.co2dice.mirai.bean.battle.Damage
import org.co2dice.mirai.bean.cards.CAOPot
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.dice.DiceList
import org.co2dice.mirai.bean.dice.NormalDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:39
 * @Message: Have a good time!  :)
 **/
class SkillActiveEffects {

    fun blunt(input:MutableList<String>,battle: Battle,skill:SkillActive,user:Cards,target:Cards):CAOPot{
        //造成伤害，取伤害数值
        var diceList = DiceList(NormalDice())
        var damage:Damage = Damage( user, target, damageType = Damage.DamageType.BLUDGEON)
    }
}