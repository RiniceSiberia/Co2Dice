package org.co2dice.mirai.bean.card.prototype

import org.co2dice.mirai.bean.effect.prototype.Effect
import java.util.*

class EventCard(
    override val cardId : UUID,
    override val cardRealName : String,
    override val effects : MutableList<Effect> = mutableListOf()
) : Card(
    cardId = cardId,
    cardRealName = cardRealName,
    effects = effects
){
}