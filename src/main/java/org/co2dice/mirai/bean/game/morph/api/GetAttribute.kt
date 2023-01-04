package org.co2dice.mirai.bean.game.morph.api

import org.co2dice.mirai.bean.game.morph.Morph
import org.co2dice.mirai.bean.game.morph.impl.GetAttributeValueInstance

interface GetAttribute : Morph {
    fun apply(next : GetAttributeDecorator,
                     context : GetAttributeContext): GetAttributeValueInstance
}