package org.co2dice.mirai.bean.game.instance.card.venue

import org.co2dice.mirai.bean.game.instance.card.CardType
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.api.EffectTarget
import org.co2dice.mirai.bean.game.api.Possessive
import org.co2dice.mirai.bean.game.entry.card.CardEntry
import org.co2dice.mirai.bean.game.prototype.character.Chess
import org.co2dice.mirai.bean.game.prototype.effect.Effect

class VenueCardInstance (
    override val entry: CardEntry,
    override var holder: Chess? = null
) : CardInstance(entry,CardType.VENUE), Possessive, EffectTarget {
    val effect:MutableList<Effect> = mutableListOf()

}