package org.co2dice.mirai.bean.game.instance.card.event

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.api.DependPlayer
import org.co2dice.mirai.bean.game.entry.card.CardEntry
import org.co2dice.mirai.bean.game.instance.card.CardInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:18
 * @Message: Have a good time!  :)
 **/
class EventCardInstance(
    override val entry: CardEntry,
    override var holder: Player? = null
) : CardInstance(entry), DependPlayer{

}