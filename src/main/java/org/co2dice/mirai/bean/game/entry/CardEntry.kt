package org.co2dice.mirai.bean.game.entry

import org.co2dice.mirai.bean.game.instance.api.RelyToCard
import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.instance.card.CardsInstance
import org.co2dice.mirai.bean.game.prototype.Card
import java.util.*

open class CardEntry(
    val card: Card,
    val flavorText : String,
    val imgUrl : String,
    override var holder: CardEntry?
) : RelyToCard {
    var entryType = card.type
}