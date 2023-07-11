package org.co2dice.mirai.core.bean.game.zone.instance

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

sealed class StackZoneInstance<C : CardInstance>(
    override var holder: PlayerInstance,
    override val cards: MutableList<C>
    ) : CardListVessel<C>(), DependPlayer {
    override fun addCard(card: C): Boolean {
        //添加卡到最上方
        return super.addCard(card)
    }

    fun addCardToBottom(card: C):Boolean{
        //index[0]为最下方
        return insertCard(card,0)
    }

    fun randomSelectCard(): C {
        return cards.random()
    }
    //随机选择一张卡


    fun shuffle(){
        cards.shuffle()
    }
    //洗牌
}