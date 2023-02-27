package org.co2dice.mirai.bean.card.prototype

import org.co2dice.mirai.bean.API.CAO
import org.co2dice.mirai.bean.effect.prototype.Effect
import org.co2dice.mirai.utils.ItemType
import java.util.*

class ItemCard(
    override val cardId : UUID,
    override val cardRealName : String,
    override val effects : MutableList<Effect> = mutableListOf(),
    override val chaos : Int,
    override val order : Int,
    val itemType : ItemType
) : Card(
    cardId = cardId,
    cardRealName = cardRealName,
    effects = effects
), CAO {
}