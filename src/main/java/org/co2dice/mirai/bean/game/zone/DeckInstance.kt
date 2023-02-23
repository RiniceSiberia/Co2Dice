package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.instance.CardInstanceTempData
import org.co2dice.mirai.bean.game.instance.card.CardBack
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.instance.card.event.EventCardInstance
import org.co2dice.mirai.bean.game.instance.card.venue.VenueCardInstance
import org.co2dice.mirai.bean.game.prototype.character.Chess
import java.util.function.Predicate

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-23:03
 * @Message: 卡组实体，最上方一张是index[0],最下方是index[deck.size-1]
 **/
class DeckInstance(override val cards: MutableList<CardInstance>,
                   override var holder: Chess?
) : ZoneInstance {
    init {
        if (cards.size > 100) {
            throw Exception("卡组最多100张卡")
        }
        if (cards.stream().anyMatch { it is EventCardInstance || it is VenueCardInstance }) {
            //角色卡,事件卡,场地卡过滤掉
            throw Exception("卡组中有不属于该卡组的卡")
        }
    }

    override fun addCard(card: CardInstance): Boolean {
        //将卡放入卡组后洗牌
        card.onFieldData.clear()
        val c = addCardToBottom(card)
        shuffle()
        return c
    }

    override fun getCard(card: CardInstance): CardInstance?{
        //获取指定卡
        val c = super.getCard(card)
        shuffle()
        return c
    }
    override fun pickCard(function: Predicate<CardInstance>): CardInstance?{
        val c = super.pickCard(function)
        shuffle()
        //随机获取一张符合条件的卡，并只将那张卡（不包括同名卡）从牌堆中删除，洗牌
        return c
    }

    fun addCardToTop(card: CardInstance):Boolean {
        //放顶
        cards.add(0,card)
        return true
    }
    fun addCardToBottom(card: CardInstance):Boolean {
        //放底
        return cards.add(card)
    }

    fun drawCard() : CardInstance? {
        if (cards.size == 0) {
            return null
        }
        val card = cards[0]
        cards.removeAt(0)
        return card
    }
    fun checkTop(i:Int) : MutableList<CardInstance> {
        return cards.subList(0,i)
    }
    fun checkBottom(i:Int) : MutableList<CardInstance> {
        return cards.subList(cards.size-i-1,cards.size-1)
    }

    fun watchDeckTop(): CardInstance {
        val card = cards[0]
        return if (card.onFieldData.get(CardInstanceTempData.FACE_UP) as Boolean == true){
            card
        }else{
            CardBack()
        }
    }

    //翻开卡组最顶上的卡
    fun faceUpTopCard() : CardInstance {
        cards[0].onFieldData[CardInstanceTempData.FACE_UP] = true
        return watchDeckTop()
    }

    fun faceDownAllCard() {
        cards.forEach {
            it.onFieldData[CardInstanceTempData.FACE_UP] = false
        }
    }

}