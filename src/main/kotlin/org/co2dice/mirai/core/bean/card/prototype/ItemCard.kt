package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.card.prototype.api.PermanentPrototypeApi
import org.co2dice.mirai.core.bean.category.CategoryPack
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.ActivatedAbility
import org.co2dice.mirai.core.bean.effect.static_ability.prototype.StaticAbility
import org.co2dice.mirai.core.bean.effect.triggered_ability.prototype.TriggeredAbility
import org.co2dice.mirai.core.publicEnums.ItemType
import java.util.*

class ItemCard(
    uuid : UUID,
    cardRealName : String,
    types : CategoryPack,
    override val chaos : Int,
    override val order : Int,
    val occupy : Map<ItemType,Int>,
    //道具卡占用的道具格，比如钢铁侠的战甲，会占满头胸腿脚手五个道具格，value代表占用目标位置几个格，比如来复枪就要两只手端着
    override val activatedAbilities: List<ActivatedAbility>,
    override val staticAbilities: List<StaticAbility>,
    override val triggeredAbilities :List<TriggeredAbility>
) : Card(
    uuid = uuid,
    cardRealName = cardRealName,
    types = types
), CAO, PermanentPrototypeApi {
}