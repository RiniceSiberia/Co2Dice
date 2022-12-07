package org.co2dice.mirai.bean.cards.item

import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Cards

/**
  * @author trasgl
  * @date 2022/12/6 9:16
  * @input 
  * @return_value
  * @message
  * @log /
  */
abstract class Item : Cards {
    override val type = CardType.ITEM
}