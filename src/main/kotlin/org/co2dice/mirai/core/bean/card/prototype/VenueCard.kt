package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.card.prototype.api.PermanentPrototypeApi
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
    override val activatedAbilities: List<ActivatedAbility>,
    override val staticAbilities: List<StaticAbility>,
    override val triggeredAbilities :List<TriggeredAbility>
    ) : Card(
    uuid = uuid,
    cardRealName = cardRealName,
    types = types
),CAO, PermanentPrototypeApi {
}