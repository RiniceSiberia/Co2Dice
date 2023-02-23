package org.co2dice.mirai.bean.game.entry.card


import org.co2dice.mirai.bean.game.api.SetTokenAPI
import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.prototype.card.Card

open class CardEntry<C : Card>(
    val card: C,
    val flavorText : String,
    val imgUrl : String,
) : SetTokenAPI {
}