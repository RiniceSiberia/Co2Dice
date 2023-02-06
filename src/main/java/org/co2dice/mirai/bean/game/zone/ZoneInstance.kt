package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.cards.CardsInstance
import org.co2dice.mirai.bean.cards.api.EffectTarget
import org.co2dice.mirai.bean.cards.api.Possessive
import java.util.function.Predicate

interface ZoneInstance :Possessive,EffectTarget {
    val cards:MutableList<CardsInstance>
    fun addCard(card: CardsInstance):Boolean{
        return cards.add(card)
    }
    //添加卡牌到区域
    fun get():MutableList<CardsInstance>{
        return cards
    }
    //看牌
    fun selectCard(function: Predicate<CardsInstance>):MutableList<CardsInstance>{
        return cards.stream().filter {function.test(it)}.toList().toMutableList()
    }
    //查找符合函数的所有牌
    fun randomSelectCard(): CardsInstance{
        return cards.random()
    }
    //随机选择一张卡
    fun getCard(card: CardsInstance): CardsInstance?{
        return if (cards.remove(card)){
            card
        }else{
            null
        }
    }//拿出一张指定的卡
    fun pickCard(function: Predicate<CardsInstance>): CardsInstance?{
        var card: CardsInstance? = null
        for (c in cards){
            if (function.test(c)){
                card = cards.removeAt(cards.indexOf(c))
            }
        }
        //随机获取一张符合条件的卡，并只将那张卡（不包括同名卡）从牌堆中删除，洗两次牌
        return card
    }

    fun contain(card: CardsInstance):Boolean{
        return cards.contains(card)
    }
    //检查手牌中是否有指定的卡
    fun removed(card: CardsInstance) {
        cards.remove(card)
    }
    fun shuffle(){
        cards.shuffle()
    }
    //洗牌
}