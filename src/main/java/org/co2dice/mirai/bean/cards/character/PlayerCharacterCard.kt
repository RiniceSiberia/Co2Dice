package org.co2dice.mirai.bean.cards.character

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.game.zone.Deck
import org.co2dice.mirai.bean.game.zone.Hand
import org.co2dice.mirai.bean.cards.item.ItemCard
import org.co2dice.mirai.bean.cards.effect.Effect
import org.co2dice.mirai.bean.tokens.TokenPool
import java.util.UUID

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-23:06
 * @Message: Have a good time!  :)
 **/
class PlayerCharacterCard(
    override val cardId: UUID = UUID.randomUUID(),
    override var cardName: String,
    override var flavorText: String,
    override var imgUrl: String,
    override var characterHolder: Player?,
) : CharacterCard() {
    override var tokens = TokenPool( this ).addRandomHumanFuller()
    override val deck: Deck = Deck("${cardId}.deck","${cardName}的卡组",CardType.CHARACTER, mutableListOf())
    override val hand: Hand = Hand(this, mutableListOf())


    override val effects: MutableSet<Effect> = mutableSetOf()
    override val items: MutableMap<ItemCard, Int> = mutableMapOf()

    fun reloadTokens() {
        tokens = TokenPool( holder = this).addRandomHumanFuller()
    }



}