package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.instance.api.Possessive
import org.co2dice.mirai.bean.game.instance.character.CharacterCard

class ZoneInstanceSet(
    override var holder: CharacterCard?

): Possessive {


    val deck : DeckInstance = DeckInstance(holder = holder)
    val hand : HandInstance = HandInstance(holder = holder)
}