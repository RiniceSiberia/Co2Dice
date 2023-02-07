package org.co2dice.mirai.bean.game.gameInstance.card.item

import org.co2dice.mirai.bean.game.gameInstance.card.api.CAO
import org.co2dice.mirai.bean.game.gameInstance.card.CardType
import org.co2dice.mirai.bean.game.gameInstance.card.CardsInstance
import org.co2dice.mirai.bean.game.gameInstance.card.api.Possessive
import org.co2dice.mirai.bean.game.gameInstance.card.character.CharacterCard
import org.co2dice.mirai.bean.game.gameInstance.card.effect.Effect

/**
  * @author trasgl
  * @date 2022/12/6 9:16
  * @input 
  * @return_value
  * @message
  * @log /
  */
abstract class ItemCard : CardsInstance(), CAO, Possessive {
    override val type = CardType.ITEM
    abstract override var holder: CharacterCard?
    //持有者可为空
    abstract val effects:MutableList<Effect>
    //效果列表
}