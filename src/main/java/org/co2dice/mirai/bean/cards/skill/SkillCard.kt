package org.co2dice.mirai.bean.cards.skill

import javax.smartcardio.Card

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-22:13
 * @Message: Have a good time!  :)
 **/
abstract class SkillCard : Card() {
    abstract val skills:MutableSet<Skill>
}