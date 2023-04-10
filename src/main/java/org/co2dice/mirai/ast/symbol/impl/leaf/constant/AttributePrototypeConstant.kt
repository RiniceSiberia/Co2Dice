package org.co2dice.mirai.ast.symbol.impl.leaf.constant

import com.google.gson.JsonElement
import org.co2dice.mirai.ast.SymbolRegistry
import org.co2dice.mirai.ast.symbol.basic.ConstantLeafSymbol
import org.co2dice.mirai.bean.attribute.prototype.AttributeAPI
import org.co2dice.mirai.bean.attribute.prototype.EliteAttribute
import org.co2dice.mirai.bean.attribute.prototype.MobAttribute

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-23:14
 * @Message: Have a good time!  :)
 **/
object AttributePrototypeConstant : ConstantLeafSymbol<AttributeAPI>(){
    init {
        SymbolRegistry.register(this)
    }
    override val natualSign: String = "Attribute"
    override fun wrapper(json: JsonElement): AttributeAPI {
        val name = json.asString
        EliteAttribute.findByName(name)?.let {
            return it
        }
        MobAttribute.findByName(name)?.let {
            return it
        }
        throw NullPointerException("Attribute $name not found")
    }
}