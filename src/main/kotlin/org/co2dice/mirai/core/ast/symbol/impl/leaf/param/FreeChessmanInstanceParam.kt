package org.co2dice.mirai.core.ast.symbol.impl.leaf.param

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-09-22:45
 * {@code @Message:} Have a good time!  :)
 **/
object FreeChessmanInstanceParam : ParamLeafSymbol<ChessmanInstance>(){

    override fun natualSign(key: String): String {
        return "$key :: ChessmanInstance(Free)"
    }

    override fun operation(key: String, params:Params): ChessmanInstance {
        return params.get(key)
    }

    override val clazz: KClass<*> = ChessmanInstance::class
}