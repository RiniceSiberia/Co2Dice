package org.co2dice.mirai.bean.game.instance.effect

import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.game.instance.card.CardsInstance
import org.co2dice.mirai.bean.game.instance.api.EffectAPI
import org.co2dice.mirai.bean.game.instance.api.EffectTarget
import org.co2dice.mirai.bean.game.instance.api.Possessive
import org.co2dice.mirai.bean.game.instance.api.RelyToCard
import org.co2dice.mirai.bean.game.instance.character.CharacterCard

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:01
 * @Message: 效果实体，可以装在技能卡，角色卡，物品卡等有持有者的卡中。
 **/
abstract class Effect(override var holder: CardsInstance?): EffectAPI<Scene, CardsInstance, CharacterCard>,
    EffectTarget, RelyToCard {



    fun getRelyCardHolder(): CharacterCard?{
        return when (holder) {
            is CharacterCard -> {
                holder as CharacterCard
            }
            is Possessive -> {
                (holder as Possessive).holder
            }
            else -> {
                null
            }
        }
    }
}