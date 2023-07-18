package org.co2dice.mirai.core.ast.symbol.impl.leaf.constant

import kotlinx.serialization.json.JsonElement
import org.co2dice.mirai.core.ast.symbol.basic.ConstantLeafSymbol

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-09-18:12
 * {@code @Message:} Have a good time!  :)
 **/
object IntegerConstant : ConstantLeafSymbol<Int>() {
    override fun wrapper(json: JsonElement): Int {
        return json.toString().toInt()
    }

    override fun natualSign(value: Int): String {
        return "$value"
    }
}