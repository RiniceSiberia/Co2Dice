package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Cards

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:26
 * @Message: Have a good time!  :)
 **/
abstract class SkillPassive(holder: Cards) : Skill(holder) {
    override val skillType = SkillType.PASSIVE

}