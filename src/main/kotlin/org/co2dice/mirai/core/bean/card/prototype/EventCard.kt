package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.category.CategoryPack
import org.co2dice.mirai.core.bean.effect.triggered_ability.prototype.TriggeredAbility
import java.util.*

class EventCard(
    uuid : UUID,
    cardRealName : String,
    types : CategoryPack,
    val triggeredAbilities :List<TriggeredAbility>
    ) : Card(
    uuid = uuid,
    cardRealName = cardRealName,
    types = types
){
}