package org.co2dice.mirai.bean.cards.event

import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Cards
import org.co2dice.mirai.bean.cards.character.CharacterCard

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:18
 * @Message: Have a good time!  :)
 **/
abstract class EventCard() : Cards() {
    override val type = CardType.EVENT
}