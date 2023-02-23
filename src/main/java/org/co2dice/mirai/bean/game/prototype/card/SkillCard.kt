package org.co2dice.mirai.bean.game.prototype.card

import org.co2dice.mirai.bean.game.api.CAO
import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.prototype.effect.Effect
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
    type = CardType.SKILL,
    effects = effects
),CAO{

}