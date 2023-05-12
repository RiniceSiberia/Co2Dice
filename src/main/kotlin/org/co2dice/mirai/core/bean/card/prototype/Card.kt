package org.co2dice.mirai.core.bean.card.prototype

import org.co2dice.mirai.core.bean.effect.prototype.Effect
import java.util.*


abstract class Card(
    open val cardId : UUID,
    open val cardRealName : String,
    open val types : Set<String>,
    open val effects: MutableList<Effect>
){



}