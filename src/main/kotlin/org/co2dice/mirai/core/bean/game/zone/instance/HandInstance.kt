package org.co2dice.mirai.core.bean.game.zone.instance

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.card.instance.MainDeckCardInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.ConstantUtils.HAND_LIMIT
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-29-22:20
 * {@code @Message:} 手牌区
 **/
class HandInstance (
    override var holder: PlayerInstance,
    override val cards: MutableList<MainDeckCardInstance> = mutableListOf(),
    var limit : Int = HAND_LIMIT
) : CardListVessel<MainDeckCardInstance>(), DependPlayer {
    fun add(card: MainDeckCardInstance): Boolean {
        //方法的返回值是用来确定是否成功添加卡片的
        if (cards.size >= limit) {
            return false
        }
        return addCard(card)
    }

    override fun constructInstance(card: CardEntry, registry: UniqueIdRegistry): MainDeckCardInstance {
        return MainDeckCardInstance(card,holder)
    }

    fun discardEvent(list : Set<CardInstance>){
        //预留
    }

    fun get() : Set<MainDeckCardInstance>{
        return cards.toSet()
    }

}