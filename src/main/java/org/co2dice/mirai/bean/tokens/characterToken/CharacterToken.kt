package org.co2dice.mirai.bean.tokens.characterToken

import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.tokens.Token

open class CharacterToken(override val id: String, override val name: String) : Token() {
    override val type: CardType = CardType.CHARACTER
    override var point: Int = 1
}