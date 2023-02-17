package org.co2dice.mirai.bean.game.instance.card.event

import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.instance.card.CardsInstance
import org.co2dice.mirai.bean.game.instance.api.Possessive
import org.co2dice.mirai.bean.game.instance.character.CharacterCard
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
) : CardsInstance(), Possessive {
    override val type = CardType.EVENT
    override var holder: CharacterCard? = null
}