package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.api.EffectTarget
import org.co2dice.mirai.bean.game.api.Possessive
import java.util.function.Predicate

interface ZoneInstance : Possessive, EffectTarget {
    val cards:MutableList<CardInstance<Any?>>
    fun addCard(card: CardInstance<Any?>):Boolean{
        return cards.add(card)
    }
    //添加卡牌到区域
    fun get():MutableList<CardInstance<Any?>>{
        return cards
    }
    //看牌
    fun selectCard(function: Predicate<CardInstance<Any?>>):MutableList<CardInstance<Any?>>{
        return cards.stream().filter {function.test(it)}.toList().toMutableList()
    }
    //查找符合函数的所有牌
    fun randomSelectCard(): CardInstance<Any?> {
        return cards.random()
    }
    //随机选择一张卡
    fun getCard(card: CardInstance<Any?>): CardInstance<Any?>?{
        return if (cards.remove(card)){
            card
        }else{
            null
        }
    }//拿出一张指定的卡
    fun pickCard(function: Predicate<CardInstance<Any?>>): CardInstance<Any?>?{
        var card: CardInstance<Any?>? = null
        for (c in cards){
            if (function.test(c)){
                card = cards.removeAt(cards.indexOf(c))
            }
        }
        //随机获取一张符合条件的卡，并只将那张卡（不包括同名卡）从牌堆中删除，洗两次牌
        return card
    }

    fun contain(card: CardInstance<Any?>):Boolean{
        return cards.contains(card)
    }
    //检查手牌中是否有指定的卡
    fun removed(card: CardInstance<Any?>) {
        cards.remove(card)
    }
    fun shuffle(){
        cards.shuffle()
    }
    //洗牌
}