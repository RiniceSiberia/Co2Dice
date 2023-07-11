package org.co2dice.mirai.core.ast.symbol.impl.leaf.constant

import kotlinx.serialization.json.JsonElement
import org.co2dice.mirai.core.ast.symbol.basic.ConstantLeafSymbol

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-22-12:33
 * @Message: Have a good time!  :)
 **/
object UnitConstant : ConstantLeafSymbol<Unit>() {
    override fun wrapper(json: JsonElement) {
    }

    override fun natualSign(value: Unit): String {
        return "Unit"
    }
}