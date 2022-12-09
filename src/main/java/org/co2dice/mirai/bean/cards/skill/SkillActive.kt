package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.battle.Scene
import org.co2dice.mirai.bean.cards.CAOPot
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.cards.event.EventCard
import org.co2dice.mirai.bean.cards.item.ItemCard
import org.co2dice.mirai.bean.tokens.Token
import org.co2dice.mirai.bean.tokens.characterToken.CharacterToken
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

    //MutableList<String>为传参
    abstract var cost:Function3<MutableList<String>,Scene,SkillActive,List<Token>>
    //使用技能的token花费，宣言使用技能后无论怎么样都会消耗。返回一个消耗token的数组。
    abstract var check:Function2<MutableList<String>,SkillActive,Boolean>
    //检定值,宣言后会检定是否可以使用技能。
    abstract var react:Function2<MutableList<String>,Cards,Int>
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免.cards应该为技能目标。
    abstract var effect:Function4<MutableList<String>,Scene,SkillActive, Cards, CAOPot>
    //造成的特效，返回true则技能生效，返回false则技能失效。第一个cards应该为holder，第二个应为target。输出为本技能提供的混乱/秩序值。
    abstract var reactEffect:Function4<MutableList<String>,Scene,SkillActive, Cards, CAOPot>
    //反抗成功后的特效,card应为target.

}