package org.co2dice.mirai.bean.cards.skill

import org.co2dice.mirai.bean.battle.Scene
import org.co2dice.mirai.bean.cards.CAO
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.cards.event.EventCard
import org.co2dice.mirai.bean.cards.item.ItemCard
import org.co2dice.mirai.bean.tokens.Token

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:01
 * @Message: 单条技能的记录，可以装在技能卡，角色卡，物品卡等有持有者的卡中。
 **/
abstract class Skill(var skillHolder:Cards):CAO{
    abstract val skillParam:MutableMap<String,Int>
    //属性的基础数据池，包括了伤害等属性。可用于计算混乱值等。
    abstract val skillType:SkillType
    //输入的传参,用来自定义一些卡的效果。
    // 比如输入damage key，就是定义殴打的伤害。加入heal key，就是定义治疗的回复量。加入coldDown，就是定义技能的冷却时间。加入aim，就是定义技能的命中率，加入range，就是定义技能的射程。
    // 多个位置的技能对应不同的输入,可以技能带来的混乱值和秩序值的变化
    abstract var cost:Function3<MutableList<String>,Scene,SkillActive,List<Token>>
    //使用技能的token花费，宣言使用技能后无论怎么样都会消耗。返回一个消耗token的数组。
    abstract var trigger:Function2<Scene,Cards,Boolean>
    //触发条件，返回true则可以消耗cost使用技能。cards应该为holder，因为通过这张卡可以获得持有者的信息。

    fun getHolder(): CharacterCard?{
        return when (skillHolder) {
            is CharacterCard -> {
                skillHolder as CharacterCard
            }
            is ItemCard -> {
                (skillHolder as ItemCard).holder
            }
            is EventCard -> {
                null
            }
            is SkillCard -> {
                (skillHolder as SkillCard).holder
            }
            else -> {
                null
            }
        }
    }
}