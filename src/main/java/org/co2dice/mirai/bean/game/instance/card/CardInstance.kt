package org.co2dice.mirai.bean.game.instance.card

import org.co2dice.mirai.bean.game.entry.card.CardEntry
import org.co2dice.mirai.bean.game.api.EffectTarget
import org.co2dice.mirai.bean.game.instance.CardInstanceTempData

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
abstract class CardInstance(open val entry: CardEntry) : EffectTarget {
    val onFieldData : MutableMap<CardInstanceTempData,Any> = mutableMapOf()
}