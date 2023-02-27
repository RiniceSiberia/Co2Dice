package org.co2dice.mirai.bean.zone

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.card.instance.CardInstance
import org.co2dice.mirai.bean.card.instance.venue.VenueCardInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-02-26-0:59
 * @Message: Have a good time!  :)
 **/
class VenueDeckInstance(override var holder: Player?, override val cards: MutableList<CardInstance>) : DeckInstanceStack(cards,holder){
    //场地卡组，类比于游戏王的场地卡组，或者万智牌的场地卡组

    init {
        val illegalCards = cards.filter { !typeLegal(it) }
        if (illegalCards.isNotEmpty()) {
            throw IllegalArgumentException("卡组中有不合法的卡片")
        }
        if (!countLegal()) {
            throw IllegalArgumentException("卡组中卡片数量不合法")
        }
    }

    override fun typeLegal(card: CardInstance): Boolean {
        return card is VenueCardInstance
    }
    override fun countLegal(): Boolean {
        return cards.size in 0..15
    }


}