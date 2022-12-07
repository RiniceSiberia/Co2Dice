package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.tokens.characterToken.CharacterToken

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:30
 * @Message: Have a good time!  :)
 **/
abstract class SkillActive : Skill() {
    override val skillType = SkillType.ACTIVE
    abstract val coldDownTime: Int
    abstract val cost:MutableMap<CharacterToken,Int>
}