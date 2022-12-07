package org.co2dice.mirai.bean.cards.event

import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Cards

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:18
 * @Message: Have a good time!  :)
 **/
abstract class Event() : Cards {
    override val type = CardType.EVENT
}