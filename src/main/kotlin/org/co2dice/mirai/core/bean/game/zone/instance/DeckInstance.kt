package org.co2dice.mirai.core.bean.game.zone.instance

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.MainDeckCardInstance
import org.co2dice.mirai.core.bean.game.zone.entry.DeckEntry
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2022-12-05-23:03
 * {@code @Message:} 卡组实体，最上方一张是index[0],最下方是index[deck.size-1]
 * 子类是场地卡卡组
 **/
open class DeckInstance(
    override val cards: MutableList<MainDeckCardInstance>,
    holder: PlayerInstance,
) : StackZoneInstance<MainDeckCardInstance>(holder,cards) {
    //初始化完毕后进行检测，如果卡片不合法则抛出异常，并将错误的卡片筛掉
    init {
        if (!countLegal()) {
            throw IllegalArgumentException("卡组中卡片数量不合法")
        }
    }
    constructor(registry : UniqueIdRegistry, deck : DeckEntry, player: PlayerInstance = PlayerInstance(deck.holder)) : this(
        holder = player,
        cards = deck.main.map { MainDeckCardInstance(
            entry = it,
            holder = player
            ) }.toMutableList(),
    )


    open fun draw() : MainDeckCardInstance? {
        //抽卡
        return cards.removeFirstOrNull()
    }

    fun countLegal() : Boolean{
        return cards.size in 0..100
    }

    override fun constructInstance(card: CardEntry, registry: UniqueIdRegistry): MainDeckCardInstance {
        return MainDeckCardInstance(card,holder)
    }

    override fun addCard(card: MainDeckCardInstance): Boolean {
        //将卡放入卡组后洗牌
        return countLegal() && super.addCard(card).also { shuffle() }
    }

    override fun pickCard(function: (MainDeckCardInstance) -> Boolean): MainDeckCardInstance? {
        return super.pickCard(function).also { shuffle() }
    }

    fun getTopCard() : MainDeckCardInstance? {
        return try {
            cards.last()
        }catch (e:NoSuchElementException){
            null
        }
    }

    fun getBottomCard() : MainDeckCardInstance? {
        return try {
            cards.first()
        }catch (e:NoSuchElementException){
            null
        }
    }

    fun checkTop(i:Int) : List<MainDeckCardInstance>? {
        return if (cards.size < i) {
            null
        } else {
            cards.subList(cards.size-i-1,cards.size-1)
        }
    }
    //查看卡组最顶上的i张卡
    fun checkBottom(i:Int) : List<MainDeckCardInstance>? {
        return if (cards.size < i) {
            null
        } else {
            return cards.subList(0,i)
        }
    }

    //看卡组
    fun watch() : MainDeckCardInstance? {
        return if (getTopCard()?.open == true){
            getTopCard()
        }else{
            null
        }
    }

}