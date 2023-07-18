package org.co2dice.mirai.core.ast.symbol.impl.leaf.param

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.symbol.basic.ParamLeafSymbol
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import java.util.stream.Stream
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-09-19:57
 * {@code @Message:} 棋子列表的param节点，主要用来获取一个区域的所有棋子
 **/
object StreamChessmanParam : ParamLeafSymbol<Stream<ChessmanInstance>>(){

    override fun natualSign(key: String): String {
        return "$key :: Stream<ChessmanInstance>"
    }

    override fun operation(key: String, params: Params): Stream<ChessmanInstance> {
        return params.get(key)
    }

    override val clazz: KClass<Stream<*>> = Stream::class

}