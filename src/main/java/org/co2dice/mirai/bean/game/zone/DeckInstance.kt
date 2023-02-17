package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.instance.card.CardsInstance
import org.co2dice.mirai.bean.game.instance.card.event.EventCard
import org.co2dice.mirai.bean.game.instance.card.venue.VenueCard
import org.co2dice.mirai.bean.game.instance.character.CharacterCard
import java.util.function.Predicate

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-23:03
 * @Message: 卡组实体，最上方一张是index[0],最下方是index[deck.size-1]
 **/
class DeckInstance(val id: String, var name: String, override var cards: MutableList<CardsInstance>, override var holder: CharacterCard?
) : ZoneInstance {
    init {
        if (cards.size > 100) {
            throw Exception("卡组最多100张卡")
        }
        if (cards.stream().anyMatch { it is CharacterCard || it is EventCard || it is VenueCard }) {
            //角色卡,事件卡,场地卡过滤掉
            throw Exception("卡组中有不属于该卡组的卡")
        }
    }

    override fun addCard(card: CardsInstance): Boolean {
        return addCardToBottom(card)
    }

    override fun getCard(card: CardsInstance): CardsInstance?{
        val c = super.getCard(card)
        shuffle()
        return c
    }
    override fun pickCard(function: Predicate<CardsInstance>): CardsInstance?{
        val c = super.pickCard(function)
        shuffle()
        //随机获取一张符合条件的卡，并只将那张卡（不包括同名卡）从牌堆中删除，洗牌
        return c
    }

    fun addCardToTop(card: CardsInstance):Boolean {
        card.faceUp = false
        card.tap = false
        cards.add(0,card)
        return true
    }
    fun addCardToBottom(card: CardsInstance):Boolean {
        card.faceUp = false
        card.tap = false
        return cards.add(card)
    }

    fun drawCard() : CardsInstance? {
        if (cards.size == 0) {
            return null
        }
        val card = cards[0]
        cards.removeAt(0)
        return card
    }
    fun checkTop(i:Int) : MutableList<CardsInstance> {
        return cards.subList(0,i)
    }
    fun checkBottom(i:Int) : MutableList<CardsInstance> {
        return cards.subList(cards.size-i-1,cards.size-1)
    }

    fun watchDeckTop(): CardsInstance {
        val card = cards[0]
        return if (card.faceUp){
            card
        }else{
            org.co2dice.mirai.bean.game.instance.card.CardBack(card.type)
        }
    }
}