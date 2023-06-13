package org.co2dice.mirai.core.ast.symbol.basic.api.stream

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol
import java.util.stream.Stream
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-31-23:50
 * @Message: Have a good time!  :)
 **/
abstract class StreamParamSymbolApi<E : Any> : ParamLeafSymbol<Stream<E>>(){

    override fun operation(key: String, params:Params): Stream<E> {
        return params.get(key)
    }

    override val clazz: KClass<*> = Stream::class
}