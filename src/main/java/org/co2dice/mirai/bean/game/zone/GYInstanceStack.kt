package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.instance.card.CardInstance

class GYInstanceStack(override var holder: Player?)
    : StackZoneInstance() {
    override val cards: MutableList<CardInstance> = mutableListOf()
}