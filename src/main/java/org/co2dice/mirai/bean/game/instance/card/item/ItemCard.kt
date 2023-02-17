package org.co2dice.mirai.bean.game.instance.card.item

import org.co2dice.mirai.bean.game.instance.api.CAO
import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.instance.card.CardsInstance
import org.co2dice.mirai.bean.game.instance.api.Possessive
import org.co2dice.mirai.bean.game.instance.character.CharacterCard
import org.co2dice.mirai.bean.game.instance.effect.Effect

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