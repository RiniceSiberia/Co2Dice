package org.co2dice.mirai.bean.card.instance

import org.co2dice.mirai.bean.card.entry.CardEntry
import org.co2dice.mirai.bean.api.EffectTarget
import org.co2dice.mirai.publicEnums.CardInstanceTempData
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
abstract class CardInstance(
    open val entry: CardEntry,
    open var cardName : String = entry.cardAlias,
    //卡片在场上作为的名字，读取别名，而非原名，用以区分
) : EffectTarget {
    val onFieldData : MutableMap<CardInstanceTempData,Any> = mutableMapOf()
}