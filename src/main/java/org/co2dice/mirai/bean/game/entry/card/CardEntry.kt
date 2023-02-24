package org.co2dice.mirai.bean.game.entry.card


import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.api.SetTokenAPI
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.instance.card.event.EventCardInstance
import org.co2dice.mirai.bean.game.instance.card.item.ItemCardInstance
import org.co2dice.mirai.bean.game.instance.card.skill.SkillCardInstance
import org.co2dice.mirai.bean.game.instance.card.venue.VenueCardInstance
import org.co2dice.mirai.bean.game.prototype.card.*
import org.co2dice.mirai.bean.game.prototype.character.Chessman

open class CardEntry(
    val card: Card,
    val flavorText : String,
    val imgUrl : String
) : SetTokenAPI {
    fun toInstance(holder: Player? = null): CardInstance {
        when(card){
            is SkillCard -> SkillCardInstance(this, holder)
            is ItemCard -> ItemCardInstance(this, holder)
            is VenueCard -> VenueCardInstance(this, holder)
            is EventCard -> EventCardInstance(this, holder)
        }
        throw Exception("CardEntry toInstance() error")
    }

}