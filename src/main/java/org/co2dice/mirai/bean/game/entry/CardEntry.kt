package org.co2dice.mirai.bean.game.entry

import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.instance.card.CardsInstance
import org.co2dice.mirai.bean.game.prototype.Card
import java.util.*

open class CardEntry(

    val flavorText : String,
    val imgUrl : String,
    val cardId : UUID
)  {


    fun toInstance() : CardsInstance {
        //搜索Card原型,将风味文字和卡图拼成一个cardsInstance

    }
}