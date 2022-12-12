package org.co2dice.mirai.bean.cards.character

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.item.ItemCard
import org.co2dice.mirai.bean.cards.skill.Skill
import org.co2dice.mirai.bean.tokens.TokenPool

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:22
 * @Message: Have a good time!  :)
 **/
abstract class CharacterCard : Cards() {
    override val type = CardType.CHARACTER
    abstract var characterHolder:Player
    abstract val skills : MutableSet<Skill>
    abstract val items : MutableMap<ItemCard,Int>
    abstract val tokens : TokenPool
    //属性和token池子
}