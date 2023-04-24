package org.co2dice.mirai.core.ast.symbol.impl.leaf.param

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol
import org.co2dice.mirai.core.bean.effect.utils.Situation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-20-23:32
 * @Message: Have a good time!  :)
 **/
object SituationParam : ParamLeafSymbol<Situation>() {
    init {
        SymbolRegistry.register(this)
    }

    override fun natualSign(key: String): String {
        return "$key :: Situation"
    }

    override fun operation(key: String, params: Params): Situation {
        return params.get(key)
    }
}