package org.co2dice.mirai.core.ast.symbol.impl.leaf.constant

import com.google.gson.JsonElement
import org.co2dice.mirai.core.ast.SymbolRegistry
import org.co2dice.mirai.core.ast.node.basic.INode
import org.co2dice.mirai.core.ast.symbol.basic.ConstantLeafSymbol
import org.co2dice.mirai.core.bean.card.instance.item.ItemCardInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-24-22:41
 * @Message: Have a good time!  :)
 **/
object NodeItemCardListConstant
    : ConstantLeafSymbol<INode<List<ItemCardInstance>>>(){
    init {
        SymbolRegistry.register(this)
    }

    override fun wrapper(json: JsonElement): INode<List<ItemCardInstance>> {
        return SymbolRegistry.deserialize(json.asJsonObject)
    }

    override fun natualSign(value: INode<List<ItemCardInstance>>): String {
        return "(INode<List<ItemCardInstance>>::${value.natualSerialize()})"
    }

}