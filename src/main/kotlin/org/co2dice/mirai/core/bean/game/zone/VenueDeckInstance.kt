package org.co2dice.mirai.core.bean.game.zone

import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.card.instance.UnPublicCardInstance
import org.co2dice.mirai.core.bean.card.instance.VenueCardInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-02-26-0:59
 * @Message: Have a good time!  :)
 **/
class VenueDeckInstance(override var holder: PlayerInstance,
                        override val cards: MutableList<UnPublicCardInstance>) : DeckInstance(cards,holder){
    //场地卡组，类比于游戏王的场地卡组，或者万智牌的场地卡组
    override fun typeLegal(card: CardInstance<*>): Boolean {
        return card is VenueCardInstance
    }
    override fun countLegal(): Boolean {
        return cards.size in 0..15
    }


}