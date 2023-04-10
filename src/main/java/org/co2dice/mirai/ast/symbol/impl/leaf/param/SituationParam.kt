package org.co2dice.mirai.ast.symbol.impl.leaf.param

import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.symbol.basic.ParamLeafSymbol
import org.co2dice.mirai.bean.effect.utils.Situation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-23:10
 * @Message: Have a good time!  :)
 **/
object SituationParam : ParamLeafSymbol<Situation>() {
    init {
        SymbolRegistry.register(this)
    }
    override val natualSign: String = "situation"
}