package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.effect.prototype.Effect
import org.co2dice.mirai.core.publicEnums.ItemType
import java.util.*

class ItemCard(
    cardId : UUID,
    cardRealName : String,
    effects : List<Effect> = mutableListOf(),
    types : Set<String>,
    override val chaos : Int,
    override val order : Int,
    val occupy : Map<ItemType,Int>
    //道具卡占用的道具格，比如钢铁侠的战甲，会占满头胸腿脚手五个道具格，value代表占用目标位置几个格，比如来复枪就要两只手端着
) : Card(
    cardId = cardId,
    cardRealName = cardRealName,
    effects = effects,
    types = types
), CAO {
}