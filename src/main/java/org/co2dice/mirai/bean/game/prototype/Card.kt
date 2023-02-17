package org.co2dice.mirai.bean.game.prototype

import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.instance.card.CardsInstance
import space.controlnet.lightioc.annotation.Provider
import java.util.*


open class Card(
    open val cardId : UUID,
    open val cardRealName : String,
    open val type : CardType
) {





}