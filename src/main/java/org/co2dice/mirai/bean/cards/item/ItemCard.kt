package org.co2dice.mirai.bean.cards.item

import org.co2dice.mirai.bean.cards.api.CAO
import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.api.Possessive
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.cards.effect.Effect

/**
  * @author trasgl
  * @date 2022/12/6 9:16
  * @input 
  * @return_value
  * @message
  * @log /
  */
abstract class ItemCard : Cards(), CAO, Possessive {
    override val type = CardType.ITEM
    abstract override var holder: CharacterCard?
    //持有者可为空
    abstract val effects:MutableList<Effect>
    //效果列表
}