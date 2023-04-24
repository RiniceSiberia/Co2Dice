package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.effect.prototype.Effect
import java.util.*

class VenueCard(
    override val cardId : UUID,
    override val cardRealName : String,
    override val effects : MutableList<Effect> = mutableListOf()
) : Card(
    cardId = cardId,
    cardRealName = cardRealName,
    effects = effects
){
}