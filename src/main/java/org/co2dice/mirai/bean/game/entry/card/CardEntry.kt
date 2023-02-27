package org.co2dice.mirai.bean.game.entry.card


import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.api.SetTokenAPI
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.instance.card.event.EventCardInstance
import org.co2dice.mirai.bean.game.instance.card.item.ItemCardInstance
import org.co2dice.mirai.bean.game.instance.card.skill.SkillCardInstance
import org.co2dice.mirai.bean.game.instance.card.venue.VenueCardInstance
import org.co2dice.mirai.bean.game.prototype.card.*

open class CardEntry(
    val card: Card,
    val flavorText : String,
    val imgUrl : String
) : SetTokenAPI {
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