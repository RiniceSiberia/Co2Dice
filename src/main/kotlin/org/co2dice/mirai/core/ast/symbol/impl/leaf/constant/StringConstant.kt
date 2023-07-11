package org.co2dice.mirai.core.ast.symbol.impl.leaf.constant

import kotlinx.serialization.json.JsonElement
import org.co2dice.mirai.core.ast.symbol.basic.ConstantLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:14
 * @Message: Have a good time!  :)
 **/
object StringConstant : ConstantLeafSymbol<String>(){
    override fun wrapper(json: JsonElement): String {
        return json.toString()
    }

    override fun natualSign(value: String): String {
        return "\"${value}\""
    }
}