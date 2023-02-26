package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.api.EffectTarget
import org.co2dice.mirai.bean.game.api.DependPlayer

abstract class StackZoneInstance : EffectTarget , CardListContainerAPI(), DependPlayer {
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

    //检查手牌中是否有指定的卡
    fun shuffle(){
        cards.shuffle()
    }
    //洗牌
}