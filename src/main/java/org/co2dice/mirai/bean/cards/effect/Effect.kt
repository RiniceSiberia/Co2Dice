package org.co2dice.mirai.bean.cards.effect

import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.cards.api.CAO
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.api.RelyToCard
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.cards.event.EventCard
import org.co2dice.mirai.bean.cards.item.ItemCard
import org.co2dice.mirai.bean.cards.skill.SkillCard
import org.co2dice.mirai.bean.tokens.Token
import org.co2dice.mirai.bean.tokens.characterToken.Dexterity
import org.co2dice.mirai.bean.tokens.characterToken.Strength

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:01
 * @Message: 效果实体，可以装在技能卡，角色卡，物品卡等有持有者的卡中。
 **/
abstract class Effect(override var holder:Cards?): CAO, RelyToCard {

//    var influence =


    var cost:Function2<Scene, Effect,List<Token>> =
        {scene,skill ->
            listOf(Strength,Strength,Dexterity)
        }
    //演示，cost为2点力量，1点敏捷
    //技能入场时的token花费，宣言使用技能后无论怎么样都会消耗。返回一个消耗token的数组。
    abstract var trigger:Function2<Scene,Cards,Boolean>
    //触发条件，返回true则可以消耗cost使用技能。cards应该为holder，因为通过这张卡可以获得持有者的信息。




    fun getHolder(): CharacterCard?{
        return when (holder) {
            is CharacterCard -> {
                holder as CharacterCard
            }
            is ItemCard -> {
                (holder as ItemCard).holder
            }
            is EventCard -> {
                null
            }
            is SkillCard -> {
                (holder as SkillCard).holder
            }
            else -> {
                null
            }
        }
    }
}