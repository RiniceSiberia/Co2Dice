package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.cards.CAO
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.character.CharacterCard

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-22:13
 * @Message: Have a good time!  :)
 **/
abstract class SkillCard : Cards(), CAO {
    abstract val skills:MutableSet<Skill>
    abstract var holder: CharacterCard?
    //持有者可为空
}