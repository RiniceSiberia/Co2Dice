package org.co2dice.mirai.bean.game.objective

import org.co2dice.mirai.bean.cards.Cards

class CardsObjective(val cards : List<Cards>) : TargetObjective<List<Cards>> {
    override fun getTarget(): List<Cards> {
        return cards
    }
}