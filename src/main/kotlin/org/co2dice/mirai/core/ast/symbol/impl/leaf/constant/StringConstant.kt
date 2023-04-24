package org.co2dice.mirai.core.ast.symbol.impl.leaf.constant

import com.google.gson.JsonElement
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.symbol.basic.ConstantLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:14
 * @Message: Have a good time!  :)
 **/
object StringConstant : ConstantLeafSymbol<String>(){
    init {
        SymbolRegistry.register(this)
    }
    override fun wrapper(json: JsonElement): String {
        return json.asString
    }

    override fun natualSign(value: String): String {
        return "\"${value}\""
    }
}