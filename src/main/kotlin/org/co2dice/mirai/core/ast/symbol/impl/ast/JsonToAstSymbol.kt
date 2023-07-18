package org.co2dice.mirai.core.ast.symbol.impl.ast

import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.UniOpSymbol
import org.co2dice.mirai.core.ast.tree.AstTree

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-17-23:27
 * @Message: Have a good time!  :)
 **/
object JsonToAstSymbol : UniOpSymbol<AstTree,JsonObject>() {
    override fun natualSign(input: INode<out JsonObject>): String {
        return "${input.natualSerialize()}.toAst()"
    }

    override fun operation(input: JsonObject, params: Params): AstTree {
        return AstTree(input)
    }
}