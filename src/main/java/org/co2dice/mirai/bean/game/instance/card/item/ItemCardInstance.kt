package org.co2dice.mirai.bean.game.instance.card.item

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.api.DependPlayer
import org.co2dice.mirai.bean.game.entry.card.CardEntry

/**
  * @author trasgl
  * @date 2022/12/6 9:16
  * @input 
  * @return_value
  * @message
  * @log /
  */
class ItemCardInstance(
    override val entry: CardEntry,
    override var holder: Player? = null
    //持有者可为空
) : CardInstance(entry), DependPlayer {


}