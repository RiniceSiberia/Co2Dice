package org.co2dice.mirai.core.ast.symbol.impl.leaf.param.situation

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.symbol.basic.SituationLeafSymbol
import org.co2dice.mirai.core.utils.situation.SituationApi

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-20-23:32
 * {@code @Message:} Have a good time!  :)
 **/
object BaseSituationSymbol : SituationLeafSymbol<SituationApi>() {
    override fun natualSign(): String {
        return "BaseSituation"
    }

    override fun operation(params:Params): SituationApi {
        return params.situation
    }
}