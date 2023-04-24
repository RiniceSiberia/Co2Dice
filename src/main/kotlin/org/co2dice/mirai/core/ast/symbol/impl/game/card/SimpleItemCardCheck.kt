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
object SimpleItemCardCheck : BiOpSymbol<List<ItemCardInstance>,
    List<ItemCardInstance>,
    INode<Boolean>>() {
    init {
        SymbolRegistry.register(this)
    }
    override fun natualSign(left: INode<List<ItemCardInstance>>, right: INode<INode<Boolean>>): String {
        return "$left.simpleItemCardCheck($right)"
    }

    override fun operation(l: List<ItemCardInstance>, r: INode<Boolean>): List<ItemCardInstance> {
        val param = Params(mutableMapOf(INNER_AST_SIGN to l))
        try {
            if (r.evaluate(param)){
                return l
            }
            return listOf()
        }catch (e: Exception){
            throw e
        }
    }


}