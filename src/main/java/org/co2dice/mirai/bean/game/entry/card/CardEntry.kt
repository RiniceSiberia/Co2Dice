package org.co2dice.mirai.bean.game.entry.card


import org.co2dice.mirai.bean.game.api.SetTokenAPI
import org.co2dice.mirai.bean.game.prototype.Card

open class CardEntry<T:Card>(
    val card: T,
    val flavorText : String,
    val imgUrl : String,
) : SetTokenAPI {
    var entryType = card.type
}