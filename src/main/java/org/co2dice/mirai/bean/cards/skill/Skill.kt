package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.battle.Battle
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.tokens.Token

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:01
 * @Message: Have a good time!  :)
 **/
abstract class Skill(){
    abstract val skillType:SkillType
    abstract fun trigger(battle:Battle,user:CharacterCard):Boolean
    //触发条件，返回true则可以消耗cost使用技能
    abstract val cost:Function1<CharacterCard,List<Token>>
    //使用技能的token花费，宣言使用技能后无论怎么样都会消耗
    abstract fun check(user:CharacterCard):Boolean
    //检定值,宣言后会检定是否可以使用技能
    abstract fun react(target:CharacterCard):Int
    //反抗值
    abstract fun effect(battle:Battle, user:CharacterCard, target:CharacterCard):Boolean
    //造成的特效
}