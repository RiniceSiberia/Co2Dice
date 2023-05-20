package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.effect.prototype.Effect
import java.util.*

class VenueCard(
    cardId : UUID,
    cardRealName : String,
    types : Set<String>,
    effects : List<Effect> = mutableListOf()
) : Card(
    cardId = cardId,
    cardRealName = cardRealName,
    effects = effects,
    types = types
){
}