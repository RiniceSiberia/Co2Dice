package org.co2dice.mirai.core.ast.symbol.basic.api.stream

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import java.util.stream.Stream

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-31-23:50
 * {@code @Message:} Have a good time!  :)
 **/
abstract class ToStreamApi<E : Any> : UniOpSymbol<Stream<E>, Collection<E>>() {
    override fun operation(input: Collection<E>, params:Params): Stream<E> {
        return input.stream()
    }
}