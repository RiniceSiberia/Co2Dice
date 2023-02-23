package org.co2dice.mirai.bean.game.instance.card.item

import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.api.Possessive
import org.co2dice.mirai.bean.game.entry.card.CardEntry
import org.co2dice.mirai.bean.game.prototype.character.Chess
import org.co2dice.mirai.bean.game.prototype.card.Card
import org.co2dice.mirai.bean.game.prototype.card.ItemCard

/**
  * @author trasgl
  * @date 2022/12/6 9:16
  * @input 
  * @return_value
  * @message
  * @log /
  */
class ItemCardInstance(
    override val entry: CardEntry<ItemCard>,
    override var holder: Chess? = null
    //持有者可为空
) : CardInstance(entry,CardType.ITEM), Possessive {


}