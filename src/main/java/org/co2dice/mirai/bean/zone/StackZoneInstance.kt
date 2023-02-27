package org.co2dice.mirai.bean.zone

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.card.instance.CardInstance
import org.co2dice.mirai.bean.API.EffectTarget
import org.co2dice.mirai.bean.API.DependPlayer

class StackZoneInstance(override var holder: Player?, override val cards: MutableList<CardInstance>) : EffectTarget, CardListContainerAPI(),
    DependPlayer {
    fun addCardToTop(card: CardInstance):Boolean{
        //index[cards.size-1]为最上方
        return addCard(card)
    }

    fun addCardToBottom(card: CardInstance):Boolean{
        //index[0]为最下方
        return insertCard(card,0)
    }
    //添加卡牌到区域底部，头部代表最上方

    //看全部牌

    fun randomSelectCard(): CardInstance {
        return cards.random()
    }
    //随机选择一张卡

    fun moveAllCardTo(target: CardListContainerAPI): Boolean {
        if (cards.isEmpty()) {
            return false
        }
        for (card in cards) {
            move(card, target)
        }
        return true
    }


    fun shuffle(){
        cards.shuffle()
    }
    //洗牌
}