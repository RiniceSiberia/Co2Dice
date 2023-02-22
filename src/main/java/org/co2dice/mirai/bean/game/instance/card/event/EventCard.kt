package org.co2dice.mirai.bean.game.instance.card.event

import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.api.Possessive
import org.co2dice.mirai.bean.game.prototype.Card
import org.co2dice.mirai.bean.game.prototype.character.Chess
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:18
 * @Message: Have a good time!  :)
 **/
class EventCard(
    override val cardId: UUID,
    override var cardName: String,
    override var flavorText: String,
    override var imgUrl: String,
) : Card(), Possessive {
    override val type = CardType.EVENT
    override var holder: Chess? = null
}