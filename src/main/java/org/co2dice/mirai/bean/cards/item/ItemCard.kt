package org.co2dice.mirai.bean.cards.item

import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.character.CharacterCard

/**
  * @author trasgl
  * @date 2022/12/6 9:16
  * @input 
  * @return_value
  * @message
  * @log /
  */
abstract class ItemCard : Cards() {
    override val type = CardType.ITEM
    abstract var holder: CharacterCard?
    //持有者可为空
}