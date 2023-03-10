package org.co2dice.mirai.bean.effect.cost

import org.co2dice.mirai.bean.effect.utils.Situation

abstract class AbstractCost {
    abstract fun check(situation: Situation):Boolean
}