package org.co2dice.mirai.core.bean.game.zone.instance

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.game.zone.api.CardVesselApi
import org.co2dice.mirai.core.utils.UniqueIdRegistry

sealed class CardListVessel<C : CardInstance> : CardVesselApi<C>{
    protected abstract val cards:MutableList<C>

    override fun addCard(card: C):Boolean{
        return cards.add(card)
    }
    //添加容器末尾

    fun addCard (card : CardEntry,registry: UniqueIdRegistry) : Boolean{
        return addCard(constructInstance(card, registry))
    }

    open fun insertCard(card: C, index: Int):Boolean{
        cards.add(index,card)
        return cards[index] == card
    }
    //添加卡牌到区域任意位置
    open fun selectCard(function: (C) -> Boolean):List<C>{
        return cards.stream().filter {function(it)}.toList().toMutableList()
    }
    //查找符合函数的所有牌

    override fun checkCard(function: (C) -> Boolean):Boolean{
        return cards.stream().anyMatch(function::invoke)
    }
    //确认符合条件的卡是否存在于容器中

    open fun pickCard(function: (C) -> Boolean): C?{
        for (c in cards){
            if (function(c)){
                cards.remove(c)
                return c
            }
        }
        return null
    }
    //拿出一张符合条件的卡，将那张卡（同条件按照容器顺序排序）从牌堆中删除

    override fun removed(card: CardInstance):Boolean{
        if (contain(card)) return cards.remove(card)
        return false
    }

    fun removedAt(index: Int):C{
        return cards.removeAt(index)
    }

    abstract fun constructInstance(card : CardEntry,registry : UniqueIdRegistry) : C

}