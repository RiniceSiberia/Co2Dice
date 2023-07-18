package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.category.CategoryPack
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.ActivatedAbility
import org.co2dice.mirai.core.bean.effect.static_ability.prototype.StaticAbility
import org.co2dice.mirai.core.bean.effect.triggered_ability.prototype.TriggeredAbility
import java.util.*

class VenueCard(
    uuid : UUID,
    cardRealName : String,
    override val chaos : Int,
    override val order : Int,
    types : CategoryPack,
    activatedAbilities: List<ActivatedAbility>,
    staticAbilities: List<StaticAbility>,
    triggeredAbilities :List<TriggeredAbility>,
    ) : Card(
    uuid = uuid,
    cardRealName = cardRealName,
    types = types,
    activatedAbilities = activatedAbilities,
    staticAbilities = staticAbilities,
    triggeredAbilities = triggeredAbilities
    ),CAO{
}