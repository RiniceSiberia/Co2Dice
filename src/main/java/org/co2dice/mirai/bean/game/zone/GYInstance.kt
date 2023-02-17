package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.instance.card.CardsInstance
import org.co2dice.mirai.bean.game.instance.character.CharacterCard

class GYInstance(override var holder: CharacterCard?, override val cards: MutableList<CardsInstance>) : ZoneInstance {
    init {
        this.cards.stream().map { it.faceUp = true }
    }
}