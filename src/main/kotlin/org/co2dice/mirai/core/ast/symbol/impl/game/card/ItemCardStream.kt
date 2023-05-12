package org.co2dice.mirai.core.ast.symbol.impl.game.card

import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.BiOpSymbol
import org.co2dice.mirai.core.bean.card.instance.item.ItemCardInstance
import org.co2dice.mirai.core.utils.ConstantUtils.INNER_AST_SIGN

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-24-22:13
 * @Message: Have a good time!  :)
 **/
object ItemCardStream : BiOpSymbol<List<ItemCardInstance>,
    List<ItemCardInstance>,
    INode<List<ItemCardInstance>>>() {
    init {
        SymbolRegistry.register(this)
    }
    override fun natualSign(left: INode<List<ItemCardInstance>>, right: INode<INode<List<ItemCardInstance>>>): String {
        return "$left.ItemCardStream($right)"
    }

    override fun operation(l: List<ItemCardInstance>, r: INode<List<ItemCardInstance>>): List<ItemCardInstance> {
        val param = Params(mutableMapOf(INNER_AST_SIGN to l))
        try {
            return r.evaluate(param)
        }catch (e: Exception){
            throw e
        }
    }


}