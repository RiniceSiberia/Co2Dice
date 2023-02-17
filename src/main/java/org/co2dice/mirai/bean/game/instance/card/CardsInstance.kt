package org.co2dice.mirai.bean.game.instance.card

import org.co2dice.mirai.bean.game.entry.CardEntry
import org.co2dice.mirai.bean.game.instance.api.EffectTarget
import org.co2dice.mirai.bean.game.prototype.Card
import java.util.UUID

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
abstract class CardsInstance : EffectTarget {
    var faceUp : Boolean = false
    var tap : Boolean = false
}