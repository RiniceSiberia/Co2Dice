package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.api.Possessive
import org.co2dice.mirai.bean.game.prototype.character.Chess

class ZoneInstanceSet(
    override var holder: Chess?

): Possessive {


    val deck : DeckInstance = DeckInstance(holder = holder)
    val hand : HandInstance = HandInstance(holder = holder)
}