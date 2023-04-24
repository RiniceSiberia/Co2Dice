package org.co2dice.mirai.core.bean.game.zone

import org.co2dice.mirai.core.bean.card.instance.CardInstance
import java.util.function.Predicate

abstract class CardsVessel<C : CardInstance> {
    protected abstract val cards:MutableList<C>

    open fun typeLegal(card: CardInstance): Boolean{
        return true
    }


    open fun addCard(card: C):Boolean{
        if(!typeLegal(card)){
            return false
        }
        return cards.add(card)
    }
    //添加容器末尾
    open fun insertCard(card: C, index: Int):Boolean{
        if(!typeLegal(card)){
            return false
        }
        cards.add(index,card)
        return cards[index] == card
    }
    //添加卡牌到区域任意位置
    open fun selectCard(function: Predicate<C>):List<CardInstance>{
        return cards.stream().filter {function.test(it)}.toList().toMutableList()
    }
    //查找符合函数的所有牌

    open fun checkCard(function: Predicate<C>):Boolean{
        return cards.stream().anyMatch(function::test)
    }
    //确认符合条件的卡是否存在于容器中
    open fun takeCard(card: C): C?{
        return pickCard { it == card }
    }
    //拿出一张指定的卡,将那张卡从牌堆中删除
    open fun pickCard(function: Predicate<C>): C?{
        val card: C? = null
        for (c in cards){
            if (function.test(c)){
                cards.remove(c)
                return card
            }
        }
        return card
    }
    //拿出一张符合条件的卡，将那张卡（同条件按照容器顺序排序）从牌堆中删除
    open fun contain(card: C):Boolean{
        return checkCard { it == card }
    }

}