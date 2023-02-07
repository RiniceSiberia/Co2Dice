package org.co2dice.mirai.bean.game.gameInstance.card

import org.co2dice.mirai.bean.game.gameInstance.card.api.EffectTarget
import java.util.UUID

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
abstract class CardsInstance : EffectTarget {
    abstract val cardId : UUID
    abstract var cardName : String
    abstract var flavorText : String
    abstract var imgUrl : String
    abstract val type : CardType
    var faceUp : Boolean = false
    var tap : Boolean = false
}