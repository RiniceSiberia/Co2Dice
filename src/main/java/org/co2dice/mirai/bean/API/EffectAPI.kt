package org.co2dice.mirai.bean.API

import org.co2dice.mirai.utils.Situation
import org.co2dice.mirai.bean.counter.Counter

interface EffectAPI {
    fun trigger(situation: Situation): Boolean
    fun cost(situation: Situation): List<Counter?>?
    val operation: Function1<Situation,Boolean>?
}