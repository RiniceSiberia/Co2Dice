package org.co2dice.mirai.core.bean.card.entry


import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.card.instance.event.EventCardInstance
import org.co2dice.mirai.core.bean.card.instance.item.ItemCardInstance
import org.co2dice.mirai.core.bean.card.instance.skill.SkillCardInstance
import org.co2dice.mirai.core.bean.card.instance.unpublic.UnPublicCardInstance
import org.co2dice.mirai.core.bean.card.prototype.*
open class CardEntry(
    val card: Card,
    val cardAlias: String = card.cardRealName,
    val flavorText : String,
    val imgUrl : String
){




    fun toUnpublicInstance() : CardInstance<*> {
        return UnPublicCardInstance(this)
    }

    fun toInstance() : CardInstance<*> {
        return when(card) {
            is ItemCard -> ItemCardInstance(this)
            is SkillCard -> SkillCardInstance(this)
            is EventCard -> EventCardInstance(this)
            else -> throw Exception("未知卡牌类型")
        }
    }

}