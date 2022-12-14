package org.co2dice.mirai.bean.cards

import org.co2dice.mirai.bean.cards.character.CharacterCard
import kotlin.streams.toList

class Hand (val holder:CharacterCard,private val cards:MutableList<Cards>){

    fun addCard(card:Cards):Boolean{
        return cards.add(card)
    }//添加卡牌到手牌

    fun showHand():MutableList<Cards>{
        return cards
    }//显示手牌

    fun selectCard(function: Function1<Cards,Boolean>):MutableList<Cards>{
        return cards.stream().filter {function.invoke(it)}.toList().toMutableList()
    }//查找手牌

    fun randomSelectCard():Cards{
        return cards.random()
    }//随机选择一张卡

    fun discard(f:Function1<Cards,Boolean>):Boolean{
        return cards.removeAll(cards.stream().filter(f).toList())
    }//弃掉符合条件的卡

    fun pickCard(card:Cards): Cards? {
        return if (cards.remove(card)){
            card
        }else{
            null
        }
    }//拿出一张指定的卡

    fun containCard(card:Cards):Boolean{
        return cards.contains(card)
    }//检查手牌中是否有指定的卡

    fun shuffle(){
        cards.shuffle()
    }//洗牌


}