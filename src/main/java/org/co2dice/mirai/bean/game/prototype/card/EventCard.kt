package org.co2dice.mirai.bean.game.prototype.card

import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.prototype.effect.Effect
import java.util.*

class EventCard(
    override val cardId : UUID,
    override val cardRealName : String,
    override val effects : MutableList<Effect> = mutableListOf()
) : Card(
    cardId = cardId,
    cardRealName = cardRealName,
    type = CardType.EVENT,
    effects = effects
){
}