package org.co2dice.mirai.bean.zone

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.publicEnums.CardInstanceTempData
import org.co2dice.mirai.bean.card.instance.CardInstance
import org.co2dice.mirai.bean.card.instance.item.ItemCardInstance
import org.co2dice.mirai.bean.card.instance.skill.SkillCardInstance
import java.util.function.Predicate

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-23:03
 * @Message: 卡组实体，最上方一张是index[0],最下方是index[deck.size-1]
 **/
open class DeckInstanceStack(override val cards: MutableList<CardInstance>,
                             override var holder: Player?
) : StackZoneInstance() {
    //初始化完毕后进行检测，如果卡片不合法则抛出异常，并将错误的卡片筛掉
    init {
        val illegalCards = cards.filter { !typeLegal(it) }
        if (illegalCards.isNotEmpty()) {
            throw IllegalArgumentException("卡组中有不合法的卡片")
        }
        if (!countLegal()) {
            throw IllegalArgumentException("卡组中卡片数量不合法")
        }
    }


    open fun countLegal() : Boolean{
        return cards.size in 0..100
    }

    override fun typeLegal(card: CardInstance): Boolean {
        return card is ItemCardInstance || card is SkillCardInstance
    }

    override fun addCard(card: CardInstance): Boolean {
        //将卡放入卡组后洗牌
        if (countLegal() && super.addCard(card)){
            shuffle()
            return true
        }
        return false
    }

    override fun takeCard(card: CardInstance): CardInstance?{
        //获取指定卡
        val c = super.takeCard(card)
        shuffle()
        return c
    }
    override fun pickCard(function: Predicate<CardInstance>): CardInstance?{
        val r = super.pickCard(function)
        shuffle()
        return r
    }
    //和父类区别在于，1.拿出卡后会洗牌，2.是从容器尾部(卡组最上方)开始遍历

    fun getTopCard() : CardInstance {
        return cards.last()
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
        return if (card.onFieldData[CardInstanceTempData.FACE_UP] == true){
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