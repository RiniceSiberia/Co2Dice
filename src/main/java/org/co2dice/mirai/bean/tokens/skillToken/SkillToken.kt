package org.co2dice.mirai.bean.tokens.skillToken

import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.tokens.Token

open class SkillToken(override val id: String, override val name: String) : Token() {
    override val type = CardType.SKILL
}