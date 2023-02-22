package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.prototype.character.Chess

class GYInstance(override var holder: Chess?, override val cards: MutableList<CardInstance<Any?>>) : ZoneInstance {
    init {
        this.cards.stream().map { it.faceUp = true }
    }
}