package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.cards.CAO
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.Possessive
import org.co2dice.mirai.bean.cards.character.CharacterCard

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-22:13
 * @Message: Have a good time!  :)
 **/
abstract class SkillCard : Cards(), CAO,Possessive {
    abstract val skills:MutableSet<Skill>
    abstract override var holder: CharacterCard?
    //持有者可为空
}