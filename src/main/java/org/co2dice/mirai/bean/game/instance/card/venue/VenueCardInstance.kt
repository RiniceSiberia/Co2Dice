package org.co2dice.mirai.bean.game.instance.card.venue


import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.api.EffectTarget
import org.co2dice.mirai.bean.game.api.Possessive
import org.co2dice.mirai.bean.game.entry.card.CardEntry
import org.co2dice.mirai.bean.game.prototype.character.Chessman
import org.co2dice.mirai.bean.game.prototype.effect.Effect

class VenueCardInstance (
    override val entry: CardEntry,
    override var holder: Player? = null
) : CardInstance(entry), Possessive, EffectTarget {
    val effect:MutableList<Effect> = mutableListOf()

}