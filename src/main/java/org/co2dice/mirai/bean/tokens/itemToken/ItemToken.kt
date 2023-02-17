package org.co2dice.mirai.bean.tokens.itemToken

import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.tokens.Token

open class ItemToken(override val id: String, override val name: String) : Token() {
    override val type: CardType = CardType.ITEM
    override var point: Int = 0
}