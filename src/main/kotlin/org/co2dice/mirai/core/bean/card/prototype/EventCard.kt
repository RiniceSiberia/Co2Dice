package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.effect.prototype.Effect
import java.util.*

class EventCard(
    cardId : UUID,
    cardRealName : String,
    types : Set<String>,
    effects : MutableList<Effect> = mutableListOf()
) : Card(
    cardId = cardId,
    cardRealName = cardRealName,
    effects = effects,
    types = types
){
}