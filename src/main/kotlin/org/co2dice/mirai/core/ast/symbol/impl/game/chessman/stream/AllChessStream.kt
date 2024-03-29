package org.co2dice.mirai.core.ast.symbol.impl.game.chessman.stream

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.api.stream.StreamSymbolApi
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.utils.ConstantUtils.IT
import java.util.stream.Stream

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-31-23:04
 * {@code @Message:} any chess instance stream
 **/
object AllChessStream : StreamSymbolApi<Boolean, Stream<ChessmanInstance>>() {


    override fun natualSign(left: INode<out Stream<Stream<ChessmanInstance>>>, right: INode<out AstTree>): String {
        return "(${left.natualSerialize()}.allChessStream(${right.natualSerialize()}))"
    }

    override fun operation(l: Stream<Stream<ChessmanInstance>>, r: AstTree, params: Params): Boolean {
        val newParams = Params(params.map,params.situation)
        return l.allMatch {
            newParams.add(IT,it)
            r.execute<Boolean>(newParams).also {
                newParams.remove(IT)
            }!!
        }
    }
}