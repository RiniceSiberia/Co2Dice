package org.co2dice.mirai.bean.cards

import kotlin.streams.toList

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-23:03
 * @Message: Have a good time!  :)
 **/
class Deck(val id: String, var name: String,val type: CardType,var cards: MutableList<Cards>) {
    init {
        if (cards.size > 100) {
            throw Exception("卡组最多100张卡")
        }
        if (cards.size < 20) {
            throw Exception("卡组最少20张卡")
        }
        if (cards.stream().anyMatch { it.getType() != type }) {
            throw Exception("卡组中有不属于该卡组的卡")
        }
    }



    fun removedCard(card: Cards) {
        cards.remove(card)
    }
    fun addCard(card: Cards) {
        cards.add(card)
    }
}