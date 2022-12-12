package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.battle.Scene
import org.co2dice.mirai.bean.cards.Cards
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:30
 * @Message: 主动技能，只能在自己的回合使用。
 **/
abstract class SkillActive(holder: Cards) : Skill(holder) {
    override val skillType = SkillType.ACTIVE
    abstract val coldDownTime: Int
    abstract val playerInputParam:MutableMap<String,String>
    //玩家输入的属性，每次都有不同的值。比如输入X点力量换取X点攻击等。使用完需要清理。
    abstract var






    abstract var check:Function2<Scene,SkillActive,Int>
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