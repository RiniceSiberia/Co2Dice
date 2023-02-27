package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.instance.card.CardInstance
import java.util.function.Predicate

abstract class CardListContainerAPI {
    protected abstract val cards:MutableList<CardInstance>

    open fun typeLegal(card: CardInstance): Boolean{
        return true
    }


    open fun addCard(card: CardInstance):Boolean{
        if(!typeLegal(card)){
            return false
        }
        card.onFieldData.clear()
        return cards.add(card)
    }
    //添加容器末尾
    open fun insertCard(card: CardInstance, index: Int):Boolean{
        if(!typeLegal(card)){
            return false
        }
        card.onFieldData.clear()
        cards.add(index,card)
        return cards[index] == card
    }
    //添加卡牌到区域任意位置
    open fun selectCard(function: Predicate<CardInstance>):List<CardInstance>{
        return cards.stream().filter {function.test(it)}.toList().toMutableList()
    }
    //查找符合函数的所有牌

    open fun checkCard(function: Predicate<CardInstance>):Boolean{
        return cards.stream().anyMatch(function::test)
    }
    //确认符合条件的卡是否存在于容器中
    open fun takeCard(card: CardInstance): CardInstance?{
        return pickCard { it == card }
    }
    //拿出一张指定的卡,将那张卡从牌堆中删除
    open fun pickCard(function: Predicate<CardInstance>): CardInstance?{
        var card: CardInstance? = null
        for (c in cards){
            if (function.test(c)){
                card = cards.removeAt(cards.indexOf(c))
                return card
            }
        }
        return card
    }
    //拿出一张符合条件的卡，将那张卡（同条件按照容器顺序排序）从牌堆中删除
    open fun contain(card: CardInstance):Boolean{
        return checkCard { it == card }
    }
    open fun move(card: CardInstance, listContainer: CardListContainerAPI):Boolean{
        val c = takeCard(card)
        return if (c != null){
            listContainer.addCard(c)
        }else{
            return false
        }
    }
    //将卡片移动到其他区域
}