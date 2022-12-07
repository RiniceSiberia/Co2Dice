package org.co2dice.mirai.bean.tokens.skillToken

import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.tokens.EternalToken
import org.co2dice.mirai.bean.tokens.Token
import org.co2dice.mirai.bean.tokens.TokenFuller

open class SkillToken(override val id: String, override val name: String) : EternalToken , Token {
    override val type = CardType.SKILL
    override fun addEvent(fuller: TokenFuller): Boolean {
        return true
    }

    override fun deleteEvent(fuller: TokenFuller): Boolean {
        return true
    }

}