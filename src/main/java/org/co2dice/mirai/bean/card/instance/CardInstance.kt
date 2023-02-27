package org.co2dice.mirai.bean.card.instance

import org.co2dice.mirai.bean.card.entry.CardEntry
import org.co2dice.mirai.bean.API.EffectTarget
import org.co2dice.mirai.bean.card.CardInstanceTempData

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
abstract class CardInstance(open val entry: CardEntry) : EffectTarget {
    val onFieldData : MutableMap<CardInstanceTempData,Any> = mutableMapOf()
}