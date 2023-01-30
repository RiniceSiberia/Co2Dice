package org.co2dice.mirai.bean.cards.cost

import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.Situation
import org.co2dice.mirai.bean.cards.api.EffectAPI
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.game.Scene

abstract class AbstractCost {
    abstract fun check(situation: Situation):Boolean
}