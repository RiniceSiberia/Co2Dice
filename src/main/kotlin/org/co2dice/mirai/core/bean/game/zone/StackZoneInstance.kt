package org.co2dice.mirai.core.bean.game.zone

import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

open class StackZoneInstance<C : CardInstance>(
    override var holder: PlayerInstance,
    override val cards: MutableList<C>
    ) : CardsVessel<C>(),
    DependPlayer<PlayerInstance> {
    fun addCardToTop(card: C):Boolean{
        //index[cards.size-1]为最上方
        return addCard(card)
    }

    fun addCardToBottom(card: C):Boolean{
        //index[0]为最下方
        return insertCard(card,0)
    }
    //添加卡牌到区域底部，头部代表最上方

    //看全部牌

    fun randomSelectCard(): C {
        return cards.random()
    }
    //随机选择一张卡


    fun shuffle(){
        cards.shuffle()
    }
    //洗牌
}