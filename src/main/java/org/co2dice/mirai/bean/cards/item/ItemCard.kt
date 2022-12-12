package org.co2dice.mirai.bean.cards.item

import org.co2dice.mirai.bean.cards.CAO
import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.Possessive
import org.co2dice.mirai.bean.cards.character.CharacterCard

/**
  * @author trasgl
  * @date 2022/12/6 9:16
  * @input 
  * @return_value
  * @message
  * @log /
  */
abstract class ItemCard : Cards(),CAO, Possessive {
    override val type = CardType.ITEM
    abstract override var holder: CharacterCard?
    //持有者可为空
}