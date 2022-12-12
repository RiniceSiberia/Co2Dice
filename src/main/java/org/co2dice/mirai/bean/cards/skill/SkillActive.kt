package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.battle.Scene
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.dice.ConstantDice
import org.co2dice.mirai.bean.dice.DiceList
import org.co2dice.mirai.bean.dice.NormalDice
import org.co2dice.mirai.bean.tokens.characterToken.CharacterToken
import org.co2dice.mirai.bean.tokens.characterToken.Dexterity
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:30
 * @Message: 主动技能，只能在自己的回合使用。
 **/
abstract class SkillActive(holder: Cards) : Skill(holder) {
    override val skillType = SkillType.ACTIVE






    var check:Function2<Scene,SkillActive,DiceList> = check@{ scene, skill ->
        val holder = skill.getHolder()
        if (holder != null){
            val tokens = holder.tokens
            //这里默认值是获取敏捷
            var fuller = tokens.getPointFuller(Dexterity)
            if (fuller != null){
                return@check DiceList(ConstantDice(fuller.getPoints()),NormalDice(20))
            }
        }

        return@check DiceList(ConstantDice(0))
    }
    //检定值,宣言后会检定是否可以使用技能。传参:玩家的输入值，场景，技能本身。返回值:检定值
    abstract var react:Function3<Scene,SkillActive, Cards, Int>
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免。 传参:玩家的输入值，场景，技能本身，敌人。返回值:反抗值
    abstract var effect:Function3<Scene,SkillActive, Cards, Boolean>
    //造成的特效。
    // 传参:玩家的输入值，场景，技能本身，敌人。
    abstract var reactEffect:Function3<Scene,SkillActive, Cards, Boolean>
    //反抗成功后的特效。
    // 传参:玩家的输入值，场景，技能本身，敌人。

}