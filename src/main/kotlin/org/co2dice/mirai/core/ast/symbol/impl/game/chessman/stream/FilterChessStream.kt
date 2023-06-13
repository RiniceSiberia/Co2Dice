package org.co2dice.mirai.core.ast.symbol.impl.game.chessman.stream

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpFunctionSymbol
import org.co2dice.mirai.core.ast.tree.AstTree
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import java.util.stream.Stream

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-02-22:59
 * @Message: Have a good time!  :)
 **/
object FilterChessStream : UniOpFunctionSymbol<List<ChessmanInstance>, Stream<ChessmanInstance>, Boolean>() {

    override fun natualSign(obj: INode<Stream<ChessmanInstance>>, function: AstTree): String {
        return "${obj.natualSerialize()}.filter<Chessman>{${function.natualSerialize()}}"
    }

    override fun operation(obj: Stream<ChessmanInstance>, function: AstTree, params: Params): List<ChessmanInstance> {
        return obj.filter { execute(it,function, params) == true }.toList()
    }
}