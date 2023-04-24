package org.co2dice.mirai.core.ast.symbol.impl.game.dice

import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.ListOpSymbol
import org.co2dice.mirai.core.bean.dice.single.api.AbstractDice
import org.co2dice.mirai.core.bean.dice.diceList.DiceList as Dices

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-10-18:00
 * @Message: Have a good time!  :)
 **/
object DiceListSymbol : ListOpSymbol<Dices, AbstractDice>() {
    init {
        SymbolRegistry.register(this)
    }

    override fun operation(list: List<AbstractDice>): Dices {
        return Dices(list)
    }

    override fun natualSign(list: List<INode<out AbstractDice>>): String {
        return "DiceList[${list.joinToString("+") { it.natualSerialize() }}]"
    }

}