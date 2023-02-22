package org.co2dice.mirai.bean.game.api

import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.instance.card.Situation
import org.co2dice.mirai.bean.game.prototype.character.Chess
import org.co2dice.mirai.bean.tokens.Token

interface EffectAPI {
    fun trigger(situation: Situation<*,*>): Boolean
    fun cost(situation: Situation<*,*>): List<Token?>?
    val operation: Function1<Situation<*,*>,Boolean>?
}