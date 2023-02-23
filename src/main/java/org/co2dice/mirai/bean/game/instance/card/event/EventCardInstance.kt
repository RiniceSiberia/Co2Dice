package org.co2dice.mirai.bean.game.instance.card.event

import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.api.Possessive
import org.co2dice.mirai.bean.game.entry.card.CardEntry
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.prototype.character.Chess
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:18
 * @Message: Have a good time!  :)
 **/
class EventCardInstance(
    override val entry: CardEntry,
    override var holder: Chess? = null
) : CardInstance(entry, CardType.EVENT), Possessive{

}