package org.co2dice.mirai.core.ast.symbol.impl.game.chessman.stream

import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.api.stream.ToStreamApi
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-09-19:45
 * @Message: Have a good time!  :)
 **/
object ChessToStream : ToStreamApi<ChessmanInstance>() {
    override fun natualSign(input: INode<out Collection<ChessmanInstance>>): String {
        return "(${input.natualSerialize()}.ChessmanToStream())"
    }
}