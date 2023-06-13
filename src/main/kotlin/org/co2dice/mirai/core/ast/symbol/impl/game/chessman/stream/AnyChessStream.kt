package org.co2dice.mirai.core.ast.symbol.impl.game.chessman.stream

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import java.util.stream.Stream
import org.co2dice.mirai.core.utils.ConstantUtils.IT

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-31-23:04
 * @Message: any chess instance stream
 **/
object AnyChessStream : BiOpSymbol<Boolean, Stream<ChessmanInstance>, INode<Boolean>>() {
    override fun natualSign(left: INode<Stream<ChessmanInstance>>, right: INode<INode<Boolean>>): String {
        return "${left.natualSerialize()}.anyChessStream(${right.natualSerialize()})"
    }

    override fun operation(l: Stream<ChessmanInstance>, r: INode<Boolean>, params:Params): Boolean {
        val newParams = Params(params.map,params.situation)
        return l.anyMatch {
            newParams.add(IT,it)
            r.evaluate(newParams).also {
                newParams.remove(IT)
            }
        }
    }
}