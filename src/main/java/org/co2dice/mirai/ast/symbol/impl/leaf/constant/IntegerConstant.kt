package org.co2dice.mirai.ast.symbol.impl.leaf.constant

import com.google.gson.JsonElement
import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.symbol.basic.ConstantLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:12
 * @Message: Have a good time!  :)
 **/
object IntegerConstant : ConstantLeafSymbol<Int>() {
    init {
        SymbolRegistry.register(this)
    }
    override val natualSign: String = "Int"
    override fun wrapper(json: JsonElement): Int {
        return json.asInt
    }
}