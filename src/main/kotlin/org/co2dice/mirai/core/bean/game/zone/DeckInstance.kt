package org.co2dice.mirai.core.bean.game.zone

import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.card.instance.item.ItemCardInstance
import org.co2dice.mirai.core.bean.card.instance.skill.SkillCardInstance
import org.co2dice.mirai.core.bean.card.instance.unpublic.UnPublicCardInstance
import org.co2dice.mirai.core.bean.game.DeckEntry
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-23:03
 * @Message: 卡组实体，最上方一张是index[0],最下方是index[deck.size-1]
 **/
open class DeckInstance(
    override val cards: MutableList<UnPublicCardInstance>,
    holder: PlayerInstance,
) : StackZoneInstance<UnPublicCardInstance>(holder,cards) {
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
    constructor(registry : UniqueIdRegistry,deck : DeckEntry,player: PlayerInstance = deck.holder) : this(
        holder = player,
        cards = deck.cards.map { UnPublicCardInstance(it,registry,deck.holder) }.toMutableList(),
    )



    open fun countLegal() : Boolean{
        return cards.size in 0..100
    }

    override fun typeLegal(card: CardInstance): Boolean {
        return card is ItemCardInstance || card is SkillCardInstance
    }

    override fun addCard(card: UnPublicCardInstance): Boolean {
        //将卡放入卡组后洗牌
        if (countLegal() && super.addCard(card)){
            shuffle()
            return true
        }
        return false
    }

    override fun takeCard(card: UnPublicCardInstance): UnPublicCardInstance?{
        //获取指定卡
        val c = super.takeCard(card)
        shuffle()
        return c
    }
    //和父类区别在于，1.拿出卡后会洗牌，2.是从容器尾部(卡组最上方)开始遍历

    fun getTopCard() : UnPublicCardInstance? {
        return try {
            cards.last()
        }catch (e:NoSuchElementException){
            null
        }
    }

    fun getBottomCard() : UnPublicCardInstance? {
        return try {
            cards.first()
        }catch (e:NoSuchElementException){
            null
        }
    }

    fun checkTop(i:Int) : List<UnPublicCardInstance>? {
        return if (cards.size < i) {
            null
        } else {
            cards.subList(cards.size-i-1,cards.size-1)
        }
    }
    //查看卡组最顶上的i张卡
    fun checkBottom(i:Int) : List<UnPublicCardInstance>? {
        return if (cards.size < i) {
            null
        } else {
            return cards.subList(0,i)
        }
    }

    //看卡组
    fun watch() : UnPublicCardInstance? {
        return if (getTopCard()?.open == true){
            getTopCard()
        }else{
            null
        }
    }

}