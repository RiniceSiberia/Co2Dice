package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.instance.card.CardInstance
import java.util.function.Predicate

interface CardContainerAPI {
    val cards:MutableList<CardInstance>
    fun addCard(card: CardInstance):Boolean{
        card.onFieldData.clear()
        return cards.add(card)
    }
    //添加容器末尾
    fun insertCard(card: CardInstance, index: Int):Boolean{
        card.onFieldData.clear()
        cards.add(index,card)
        return cards[index] == card
    }
    //添加卡牌到区域任意位置
    fun getAll():List<CardInstance>{
        return cards
    }
    fun selectCard(function: Predicate<CardInstance>):List<CardInstance>{
        return getAll().stream().filter {function.test(it)}.toList().toMutableList()
    }
    //查找符合函数的所有牌
    fun getCard(card: CardInstance): CardInstance?{
        return if (cards.remove(card)){
            card
        }else{
            null
        }
    }//拿出一张指定的卡
    fun checkCard(function: Predicate<CardInstance>):Boolean{
        return cards.stream().anyMatch(function::test)
    }
    //确认符合条件的卡是否存在于容器中
    fun pickCard(function: Predicate<CardInstance>): CardInstance?{
        var card: CardInstance? = null
        for (c in cards){
            if (function.test(c)){
                card = cards.removeAt(cards.indexOf(c))
                return card
            }
        }
        return card
    }
    //随机获取一张符合条件的卡，将那张卡（同条件按照容器顺序排序）从牌堆中删除
    fun contain(card: CardInstance):Boolean{
        return cards.contains(card)
    }
    fun moveCardToElseZone(card: CardInstance, container: CardContainerAPI):Boolean{
        val c = pickCard { it == card }
        if (c != null){
            return container.addCard(c)
        }
        return false
    }
    //将卡片移动到其他区域
}