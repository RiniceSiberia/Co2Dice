package org.co2dice.mirai.bean.game.prototype

import org.co2dice.mirai.bean.game.api.CAO
import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.prototype.effect.Effect
import java.util.*


open class Card(
    open val cardId : UUID,
    open val cardRealName : String,
    open val type : CardType,
    open val effects: MutableList<Effect>,
    override val chaos: Int,
    override val order: Int
) : CAO{





}