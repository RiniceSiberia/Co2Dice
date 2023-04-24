package org.co2dice.mirai.core.bean.card.entry


import org.co2dice.mirai.core.bean.card.prototype.*
open class CardEntry(
    val card: Card,
    val cardAlias: String = card.cardRealName,
    val flavorText : String,
    val imgUrl : String
){

}