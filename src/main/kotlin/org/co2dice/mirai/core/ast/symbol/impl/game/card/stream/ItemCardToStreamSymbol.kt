package org.co2dice.mirai.core.ast.symbol.impl.game.card.stream

import org.co2dice.mirai.core.ast.node.INode
import org.co2dice.mirai.core.ast.symbol.basic.api.stream.ToStreamApi
import org.co2dice.mirai.core.bean.card.instance.ItemCardInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-24-22:13
 * @Message: 输入一个list和一个处理list的函数，返回经过函数处理的list
 **/
object ItemCardToStreamSymbol : ToStreamApi<ItemCardInstance>() {
    override fun natualSign(input: INode<Collection<ItemCardInstance>>): String {
        return "(${input.natualSerialize()}.ItemCardToStream())"
    }
}