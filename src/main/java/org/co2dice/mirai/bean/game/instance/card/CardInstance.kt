package org.co2dice.mirai.bean.game.instance.card

import org.co2dice.mirai.bean.game.entry.card.CardEntry
import org.co2dice.mirai.bean.game.api.EffectTarget

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
abstract class CardInstance<E:CardEntry<*>>(
    open val entry: E,
    var type: CardType) : EffectTarget {
    val onFieldData : MutableMap<String,Any> = mutableMapOf()
}