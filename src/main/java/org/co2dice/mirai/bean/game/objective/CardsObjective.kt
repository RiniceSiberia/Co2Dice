package org.co2dice.mirai.bean.game.objective

import org.co2dice.mirai.bean.cards.CardsInstance

class CardsObjective(val cards : List<CardsInstance>) : TargetObjective<List<CardsInstance>> {
    override fun getTarget(): List<CardsInstance> {
        return cards
    }
}