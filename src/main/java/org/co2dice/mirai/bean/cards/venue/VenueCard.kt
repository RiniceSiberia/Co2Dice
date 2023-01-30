package org.co2dice.mirai.bean.cards.venue

import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.api.Possessive
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.cards.effect.Effect
import java.util.*

class VenueCard(
    override val cardId: UUID,
    override var cardName: String,
    override var flavorText: String,
    override var imgUrl: String,
) :Cards(), Possessive {
    override val type: CardType = CardType.VENUE
    override var holder: CharacterCard? = null
    val effect:MutableList<Effect> = mutableListOf()

}