package org.co2dice.mirai.bean.card.prototype

import org.co2dice.mirai.bean.api.CAO
import org.co2dice.mirai.bean.effect.prototype.Effect
import java.util.*

class SkillCard(
    override val cardId : UUID,
    override val cardRealName : String,
    override val effects : MutableList<Effect> = mutableListOf(),
    override val chaos : Int,
    override val order : Int,

    ) : Card(
    cardId = cardId,
    cardRealName = cardRealName,
    effects = effects
), CAO {

}