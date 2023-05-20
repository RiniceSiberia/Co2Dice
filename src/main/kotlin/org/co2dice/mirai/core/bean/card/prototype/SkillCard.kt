package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.effect.prototype.Effect
import java.util.*

class SkillCard(
    cardId : UUID,
    cardRealName : String,
    types : Set<String>,
    effects : List<Effect> = mutableListOf(),
    override val chaos : Int,
    override val order : Int,
    ) : Card(
    cardId = cardId,
    cardRealName = cardRealName,
    effects = effects,
    types = types
), CAO {

}