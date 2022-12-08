package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.battle.Battle
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.tokens.Token

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:01
 * @Message: 单条技能的记录，可以装在技能卡，角色卡，事件卡中
 **/
abstract class Skill(){
    abstract val skillType:SkillType
    abstract val input:Map<String,Int>
    //输入的传参,用来自定义一些卡的效果。
    // 比如输入damage key，就是定义殴打的伤害。加入heal key，就是定义治疗的回复量。加入coldDown，就是定义技能的冷却时间。加入aim，就是定义技能的命中率，加入range，就是定义技能的射程。
    // 多个位置的技能对应不同的输入,可以技能带来的混乱值和秩序值的变化
    abstract val chaos: Int
    //技能的混乱值,由input决定
    abstract val order:Int
    //技能的秩序值,由input决定
    abstract var trigger:Function2<Battle,CharacterCard,Boolean>
    //触发条件，返回true则可以消耗cost使用技能
    abstract var cost:Function1<CharacterCard,List<Token>>
    //使用技能的token花费，宣言使用技能后无论怎么样都会消耗
    abstract var check:Function1<CharacterCard,Boolean>
    //检定值,宣言后会检定是否可以使用技能
    abstract var react:Function1<CharacterCard,Int>
    //反抗值,敌人受到技能影响后会检定是否可以反抗，若成功则豁免
    abstract var effect:List<Function3<Battle,CharacterCard,CharacterCard,Boolean>>
    //造成的特效，返回true则技能生效，返回false则技能失效。之所以是list，是因为每个效果都是散装的，可以自由组合，如临时伤害和流血效果，或者是伤害和治疗效果
    abstract var reactEffect:List<Function3<Battle,CharacterCard,CharacterCard,Boolean>>
    //反抗成功后的特效
}