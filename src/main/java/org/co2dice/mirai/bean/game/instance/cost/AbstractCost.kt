package org.co2dice.mirai.bean.game.instance.cost

import org.co2dice.mirai.bean.game.instance.card.Situation

abstract class AbstractCost {
    abstract fun check(situation: Situation):Boolean
}