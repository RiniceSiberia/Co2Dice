package org.co2dice.mirai.bean.api

import org.co2dice.mirai.bean.effect.utils.Situation

interface EffectAPI {
    val operation: ((Situation) ->Boolean)?
}