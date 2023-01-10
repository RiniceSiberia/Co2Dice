package org.co2dice.mirai.bean.cards.character

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.game.zone.Deck
import org.co2dice.mirai.bean.game.zone.Hand
import org.co2dice.mirai.bean.cards.item.ItemCard
import org.co2dice.mirai.bean.cards.effect.Effect
import org.co2dice.mirai.bean.tokens.TokenPool

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:22
 * @Message: Have a good time!  :)
 **/
abstract class CharacterCard : Cards() {
    override val type = CardType.CHARACTER
    abstract var characterHolder:Player?
    abstract val effects : MutableSet<Effect>
    abstract val items : MutableMap<ItemCard,Int>
    abstract val tokens : TokenPool
    //属性和token池子
    abstract val deck : Deck
    //卡组
    abstract val hand : Hand
    //手牌
}