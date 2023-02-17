package org.co2dice.mirai.bean.game.instance.card.venue

import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.instance.card.CardsInstance
import org.co2dice.mirai.bean.game.instance.api.EffectTarget
import org.co2dice.mirai.bean.game.instance.api.Possessive
import org.co2dice.mirai.bean.game.instance.character.CharacterCard
import org.co2dice.mirai.bean.game.instance.effect.Effect
import java.util.*

class VenueCard (
    override val cardId: UUID,
    override var cardName: String,
    override var flavorText: String,
    override var imgUrl: String,
) : CardsInstance(), Possessive, EffectTarget {
    override val type: CardType = CardType.VENUE
    override var holder: CharacterCard? = null
    val effect:MutableList<Effect> = mutableListOf()

}