package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.prototype.Effect
import java.util.*


sealed class Card(
    open val cardId : UUID,
    open val cardRealName : String,
    open val types : Set<String>,
    open val effects: List<Effect>
){



}
fun <C : Card> C.toEntry(
    cardAlias : String = cardRealName,
    flavorText : String,
    imgUrl : String,
) : CardEntry<C> {
    return CardEntry(this,cardAlias,flavorText,imgUrl)
}