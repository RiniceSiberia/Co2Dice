package org.co2dice.mirai.core.ast.symbol.impl.game.situation.stream

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.utils.situation.SituationApi
import java.util.stream.Stream

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-31-22:58
 * @Message: Have a good time!  :)
 **/
object GetInitiatorChessInstanceStream : UniOpSymbol<Stream<ChessmanInstance>, SituationApi>(){
    override fun natualSign(input: INode<SituationApi>): String {
        return "$input.getInitiatorChessInstanceStream()"
    }

    override fun operation(input: SituationApi, params:Params): Stream<ChessmanInstance> {
        return input.scene.venueMap.chessMap.keys.stream()
    }

}