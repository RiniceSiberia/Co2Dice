package org.co2dice.mirai.bean.card.instance.event

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.API.DependPlayer
import org.co2dice.mirai.bean.card.entry.CardEntry
import org.co2dice.mirai.bean.card.instance.CardInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:18
 * @Message: Have a good time!  :)
 **/
class EventCardInstance(
    override val entry: CardEntry,
    override var holder: Player? = null
) : CardInstance(entry), DependPlayer {

}