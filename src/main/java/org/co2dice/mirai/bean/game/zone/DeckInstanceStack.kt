package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.instance.CardInstanceTempData
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.instance.card.event.EventCardInstance
import org.co2dice.mirai.bean.game.instance.card.venue.VenueCardInstance
import java.util.function.Predicate

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-23:03
 * @Message: 卡组实体，最上方一张是index[0],最下方是index[deck.size-1]
 **/
class DeckInstanceStack(override val cards: MutableList<CardInstance>,
                        override var holder: Player?
) : StackZoneInstance {
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
        val b = super.addCard(card)
        shuffle()
        return b
    }

    override fun getCard(card: CardInstance): CardInstance?{
        //获取指定卡
        val c = super.getCard(card)
        shuffle()
        return c
    }
    override fun pickCard(function: Predicate<CardInstance>): CardInstance?{
        val r = super.pickCard(function)
        shuffle()
        return r
    }
    //和父类区别在于，1.拿出卡后会洗牌，2.是从容器尾部(卡组最上方)开始遍历

    fun drawCard() : CardInstance? {
        if (cards.size == 0) {
            return null
        }
        val card = cards.last()
        //顶部开始抽牌
        cards.removeLast()
        return card
    }
    fun checkTop(i:Int) : List<CardInstance>? {
        return if (cards.size < i) {
            null
        } else {
            cards.subList(cards.size-i-1,cards.size-1)
        }
    }
    //查看卡组最顶上的i张卡
    fun checkBottom(i:Int) : List<CardInstance>? {
        return if (cards.size < i) {
            null
        } else {
            return cards.subList(0,i)
        }
    }
    //查看卡组最底下的i张卡
    fun watchDeckTop(): CardInstance? {
        val card = cards.last()
        return if (card.onFieldData.get(CardInstanceTempData.FACE_UP) as Boolean == true){
            card
        }else{
            null
        }
    }
    //看卡组最顶上的卡
    fun faceUpTopCard() : CardInstance? {
        cards[0].onFieldData[CardInstanceTempData.FACE_UP] = true
        return watchDeckTop()
    }
    //将卡组最顶上的卡变为正面

    fun faceDownAllCard() {
        cards.forEach {
            it.onFieldData[CardInstanceTempData.FACE_UP] = false
        }
    }
    //将卡组所有卡变为背面

}