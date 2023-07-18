package org.co2dice.mirai.core.ast.symbol.impl.leaf.param

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-09-18:15
 * {@code @Message:} Have a good time!  :)
 **/
object IntegerParam : ParamLeafSymbol<Int>(){

    override fun natualSign(key: String): String {
        return "$key :: Integer"
    }

    override fun operation(key: String, params:Params): Int {
        return params.get(key)
    }

    override val clazz: KClass<*> = Int::class

}