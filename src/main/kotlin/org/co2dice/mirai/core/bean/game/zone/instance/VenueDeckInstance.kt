package org.co2dice.mirai.core.bean.game.zone.instance

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.card.instance.SideDeckCardInstance
import org.co2dice.mirai.core.bean.card.prototype.VenueCard
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-02-26-0:59
 * {@code @Message:} Have a good time!  :)
 **/
class VenueDeckInstance(override var holder: PlayerInstance,
                        override val cards: MutableList<SideDeckCardInstance>)
    : StackZoneInstance<SideDeckCardInstance>(holder, cards){
    //场地卡组，类比于游戏王的场地卡组，或者万智牌的场地卡组

    init {
        val illegalCards = cards.filter { !typeLegal(it) }
        if (illegalCards.isNotEmpty()) {
            throw IllegalArgumentException("卡组中有不合法的卡片")
        }
        if (!countLegal()) {
            throw IllegalArgumentException("卡组中卡片数量不合法")
        }
    }

    fun typeLegal(card: CardInstance): Boolean {
        return card.entry.prototype is VenueCard
    }

    open fun draw() : SideDeckCardInstance? {
        //抽卡
        return cards.removeFirstOrNull()
    }

    override fun constructInstance(card: CardEntry, registry: UniqueIdRegistry): SideDeckCardInstance {
        return SideDeckCardInstance(card,holder)
    }

    fun countLegal(): Boolean {
        return cards.size in 0..15
    }


}