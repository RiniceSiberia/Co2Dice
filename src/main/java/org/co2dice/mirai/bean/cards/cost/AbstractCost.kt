package org.co2dice.mirai.bean.cards.cost

import org.co2dice.mirai.bean.cards.Situation

abstract class AbstractCost {
    abstract fun check(situation: Situation):Boolean
}