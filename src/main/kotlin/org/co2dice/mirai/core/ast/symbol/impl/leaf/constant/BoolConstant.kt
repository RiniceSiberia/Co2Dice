package org.co2dice.mirai.core.ast.symbol.impl.leaf.constant

import com.google.gson.JsonElement
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.symbol.basic.ConstantLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:13
 * @Message: Have a good time!  :)
 **/
object BoolConstant : ConstantLeafSymbol<Boolean>(){
    init {
        SymbolRegistry.register(this)
    }
    override fun wrapper(json: JsonElement): Boolean {
        return json.asBoolean
    }

    override fun natualSign(value: Boolean): String {
        return "$value"
    }
}