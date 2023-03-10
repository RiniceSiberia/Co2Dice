package org.co2dice.mirai.bean.card.instance.venue


import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.card.instance.CardInstance
import org.co2dice.mirai.bean.api.EffectTarget
import org.co2dice.mirai.bean.api.DependPlayer
import org.co2dice.mirai.bean.card.entry.CardEntry
import org.co2dice.mirai.bean.effect.prototype.Effect

class VenueCardInstance (
    override val entry: CardEntry,
    override var holder: Player? = null
) : CardInstance(entry), DependPlayer, EffectTarget {
    val effect:MutableList<Effect> = mutableListOf()

}