package org.co2dice.mirai.bean.game.instance.card

import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-17-18:50
 * @Message: Have a good time!  :)
 **/
class CardBack(override val type: CardType) : org.co2dice.mirai.bean.game.instance.card.CardInstance<Any?>(null) {
    override val cardId: UUID = UUID.randomUUID()
    override var cardName: String = "未知卡片"
    override var flavorText: String = "NaN"
    override var imgUrl: String = ""



}