package org.co2dice.mirai.bean.tokens.skillToken

import org.co2dice.mirai.bean.game.gameInstance.card.CardType
import org.co2dice.mirai.bean.tokens.Token

open class SkillToken(override val id: String, override val name: String) : Token() {
    override val type = CardType.SKILL
    override var point: Int = 1
}