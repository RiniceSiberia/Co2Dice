package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.category.CategoryPack
import org.co2dice.mirai.core.bean.effect.triggered_ability.prototype.TriggeredAbility
import java.util.*

class SkillCard(
    uuid : UUID,
    cardRealName : String,
    types : CategoryPack,
    val triggeredAbilities :List<TriggeredAbility>,
    override val chaos : Int,
    override val order : Int,
    ) : Card(
    uuid = uuid,
    cardRealName = cardRealName,
    types = types
), CAO {

}