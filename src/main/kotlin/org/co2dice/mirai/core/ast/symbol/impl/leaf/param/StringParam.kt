package org.co2dice.mirai.core.ast.symbol.impl.leaf.param

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-09-18:16
 * @Message: Have a good time!  :)
 **/
object StringParam : ParamLeafSymbol<String>(){

    override fun natualSign(key: String): String {
        return "$key :: String"
    }

    override fun operation(key: String, params:Params): String {
        return params.get(key)
    }

    override val clazz: KClass<*> = String::class
}