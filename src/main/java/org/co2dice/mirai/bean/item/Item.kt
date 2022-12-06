package org.co2dice.mirai.bean.item

import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Cards

abstract class Item :Cards{
    abstract var id:String
    abstract var chaos:Int
    abstract var order:Int

    override fun getType(): CardType {
        return CardType.ITEM
    }
}