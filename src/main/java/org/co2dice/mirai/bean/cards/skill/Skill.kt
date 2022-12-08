package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.battle.Battle
import org.co2dice.mirai.bean.cards.character.Character
import org.co2dice.mirai.bean.tokens.Token

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:01
 * @Message: Have a good time!  :)
 **/
abstract class Skill(){
    abstract val skillType:SkillType
    abstract val cost:Function1<Character,List<Token>>
    //使用技能的token花费，宣言使用技能后无论怎么样都会消耗
    abstract fun check(user:Character):
    //检定值
    abstract fun react(target:Character):Int
    //反抗值
    abstract fun trigger(battle:Battle,user:Character):Boolean
    //触发条件
    abstract fun effect(battle:Battle,user:Character,target:Character):Boolean
    //造成的特效
}