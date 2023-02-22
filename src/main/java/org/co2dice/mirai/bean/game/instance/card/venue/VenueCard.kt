package org.co2dice.mirai.bean.game.instance.card.venue

import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.api.EffectTarget
import org.co2dice.mirai.bean.game.api.Possessive
import org.co2dice.mirai.bean.game.prototype.character.Chess
import org.co2dice.mirai.bean.game.prototype.effect.Effect
import org.co2dice.mirai.bean.game.prototype.Card
import java.util.*

class VenueCard (
    override val cardId: UUID,
    override var cardName: String,
    override var flavorText: String,
    override var imgUrl: String,
) : CardInstance<Any?>(null), Possessive, EffectTarget {
    override val type: CardType = CardType.VENUE
    override var holder: Chess? = null
    val effect:MutableList<Effect<Card>> = mutableListOf()

}