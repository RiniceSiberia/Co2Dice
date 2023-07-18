package org.co2dice.mirai.core.ast.symbol.impl.leaf.constant

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.co2dice.mirai.core.ast.symbol.basic.ConstantLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-17-23:26
 * @Message: Have a good time!  :)
 **/
object JsonObjectConstant: ConstantLeafSymbol<JsonObject>() {
    override fun natualSign(value: JsonObject): String {
        return value.toString()
    }

    override fun wrapper(json: JsonElement): JsonObject {
        return json.jsonObject
    }
}