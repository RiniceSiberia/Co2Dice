package org.co2dice.mirai.bean.game.gameInstance.card.cost

import org.co2dice.mirai.bean.game.gameInstance.card.Situation

abstract class AbstractCost {
    abstract fun check(situation: Situation):Boolean
}