package org.co2dice.mirai.bean.cards.event

import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.CardsInstance
import org.co2dice.mirai.bean.cards.api.Possessive
import org.co2dice.mirai.bean.cards.character.CharacterCard
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