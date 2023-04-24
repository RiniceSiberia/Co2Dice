package org.co2dice.mirai.core.ast.symbol.impl.leaf.param

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-20:53
 * @Message: Have a good time!  :)
 **/
object PlayerParam : ParamLeafSymbol<PlayerInstance>() {
    init {
        SymbolRegistry.register(this)
    }

    override fun natualSign(key: String): String {
        return "$key :: Player"
    }

    override fun operation(key: String, params: Params): PlayerInstance {
        return params.get(key)
    }
}