package org.co2dice.mirai.bean.cards

import kotlin.streams.toList

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-23:03
 * @Message: 卡组实体，最上方一张是index[0],最下方是index[deck.size-1]
 **/
class Deck(val id: String, var name: String, private val type: CardType, var cards: MutableList<Cards>) {
    init {
        if (cards.size > 100) {
            throw Exception("卡组最多100张卡")
        }
        if (cards.stream().anyMatch { it.type != this.type }) {
            throw Exception("卡组中有不属于该卡组的卡")
        }
    }
    fun removedCards(card: Cards) {
        cards.remove(card)
    }
    fun insertCard(card: Cards) {
        card.faceUp = false
        card.tap = false
        cards.add(card)
        shuffle()
    }
    fun searchCard(f:Function1<Cards,Boolean>): Cards? {
        return cards.stream().filter {f.invoke(it)}.toList().getOrNull(0)
    }
    fun pickCard(function: Function1<Cards,Boolean>):Cards?{
        shuffle()
        var card:Cards? = null
        for (c in cards){
            if (function.invoke(c)){
                card = cards.removeAt(cards.indexOf(c))
            }
        }
        shuffle()
        //随机获取一张符合条件的卡，并只将那张卡（不包括同名卡）从牌堆中删除，洗两次牌
        return card
    }

    fun addCardToTop(card: Cards) {
        card.faceUp = false
        card.tap = false
        cards.add(0,card)
    }
    fun addCardToBottom(card: Cards) {
        card.faceUp = false
        card.tap = false
        cards.add(card)
    }
    fun shuffle () {
        cards = cards.shuffled().toMutableList()
    }
    fun drawCard() : Cards? {
        if (cards.size == 0) {
            return null
        }
        val card = cards[0]
        cards.removeAt(0)
        return card
    }
    fun checkTop(i:Int) : MutableList<Cards> {
        return cards.subList(0,i)
    }
    fun checkBottom(i:Int) : MutableList<Cards> {
        return cards.subList(cards.size-i-1,cards.size-1)
    }

    fun watchDeckTop():Cards{
        val card = cards[0]
        return if (card.faceUp){
            card
        }else{
            CardBack(card.type)
        }
    }

}