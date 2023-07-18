package org.co2dice.mirai.core.ast.symbol.impl.leaf.param.situation

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.symbol.basic.SituationLeafSymbol
import org.co2dice.mirai.core.utils.situation.ActivationSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-31-22:24
 * {@code @Message:} Have a good time!  :)
 **/
object ActivationSituationSymbol: SituationLeafSymbol<ActivationSituation>() {
    override fun natualSign(): String {
        return "ActivationSituation"
    }

    override fun operation(params:Params): ActivationSituation {
        return params.situation as ActivationSituation
    }
}