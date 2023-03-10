package org.co2dice.mirai.bean.card.entry


import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.card.prototype.*
import org.co2dice.mirai.bean.api.SetTokenAPI
import org.co2dice.mirai.bean.card.instance.CardInstance
import org.co2dice.mirai.bean.card.instance.event.EventCardInstance
import org.co2dice.mirai.bean.card.instance.item.ItemCardInstance
import org.co2dice.mirai.bean.card.instance.skill.SkillCardInstance
import org.co2dice.mirai.bean.card.instance.venue.VenueCardInstance

open class CardEntry(
    val card: Card,
    val cardAlias: String = card.cardRealName,
    val flavorText : String,
    val imgUrl : String
){
    fun toInstance(holder: Player? = null): CardInstance {
        when(card){
            is SkillCard -> return SkillCardInstance(this, holder)
            is ItemCard -> return ItemCardInstance(this, holder)
            is VenueCard -> return VenueCardInstance(this, holder)
            is EventCard -> return EventCardInstance(this, holder)
        }
        throw Exception("CardEntry toInstance() error")
    }

}