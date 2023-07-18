package org.co2dice.mirai.core.ast.symbol.impl.leaf.constant

import kotlinx.serialization.json.JsonElement
import org.co2dice.mirai.core.ast.symbol.basic.ConstantLeafSymbol
import org.co2dice.mirai.core.bean.attribute.prototype.Attribute
import org.co2dice.mirai.core.bean.attribute.prototype.AttributeRegistry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-09-23:14
 * {@code @Message:} Have a good time!  :)
 **/
object AttributePrototypeConstant : ConstantLeafSymbol<Attribute>(){
    override fun wrapper(json: JsonElement): Attribute {
        val name = json.toString().replace(" ","")
        AttributeRegistry.getAttribute(name)?.let {
            return it
        }
        throw NullPointerException("Attribute $name not found")
    }

    override fun natualSign(value: Attribute): String {
        return "${value.nameStr} :: Attribute"
    }
}