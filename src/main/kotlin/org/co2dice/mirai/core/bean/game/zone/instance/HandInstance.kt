package org.co2dice.mirai.core.bean.game.zone.instance

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.card.instance.MainDeckUnPublicCardInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.ConstantUtils.HAND_LIMIT
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-29-22:20
 * @Message: 手牌区
 **/
class HandInstance (
    override var holder: PlayerInstance,
    override val cards: MutableList<MainDeckUnPublicCardInstance> = mutableListOf(),
    var limit : Int = HAND_LIMIT
) : CardListVessel<MainDeckUnPublicCardInstance>(), DependPlayer {
    fun add(card: MainDeckUnPublicCardInstance): Boolean {
        //方法的返回值是用来确定是否成功添加卡片的
        if (cards.size >= limit) {
            return false
        }
        return addCard(card)
    }

    override fun constructInstance(card: CardEntry, registry: UniqueIdRegistry): MainDeckUnPublicCardInstance {
        return MainDeckUnPublicCardInstance(card,holder)
    }

    fun discard(cards : Set<CardInstance>) : Boolean{
        return this.cards.removeAll(cards).also { discardEvent(cards) }
    }
    fun discard(card : CardInstance) : Boolean{
        return this.cards.remove(card).also { discardEvent(setOf(card)) }
    }

    fun discardEvent(list : Set<CardInstance>){
        //预留
    }

}