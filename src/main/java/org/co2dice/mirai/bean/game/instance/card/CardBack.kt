package org.co2dice.mirai.bean.game.instance.card

import org.co2dice.mirai.bean.game.entry.card.CardEntry
import org.co2dice.mirai.bean.game.prototype.card.Card
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-17-18:50
 * @Message: Have a good time!  :)
 **/
class CardBack() : CardInstance(CardEntry(
    Card(UUID.randomUUID(),
        "未知卡片",
        CardType.BACK,
        mutableListOf(),
        null,
        null),
        "",
        "IMG_URL"
    ),CardType.BACK) {
}