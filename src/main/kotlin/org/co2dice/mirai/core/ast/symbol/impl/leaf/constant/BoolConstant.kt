package org.co2dice.mirai.core.ast.symbol.impl.leaf.constant

import kotlinx.serialization.json.JsonElement
import org.co2dice.mirai.core.ast.symbol.basic.ConstantLeafSymbol

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-09-18:13
 * {@code @Message:} Have a good time!  :)
 **/
object BoolConstant : ConstantLeafSymbol<Boolean>(){
    override fun wrapper(json: JsonElement): Boolean {
        return json.toString().toBoolean()
    }

    override fun natualSign(value: Boolean): String {
        return "$value"
    }
}