package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.tokens.characterToken.CharacterToken

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:30
 * @Message: 主动技能，只能在自己的回合使用。
 **/
abstract class SkillActive : Skill() {
    override val skillType = SkillType.ACTIVE
    abstract val coldDownTime: Int
}