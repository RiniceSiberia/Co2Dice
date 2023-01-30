package org.co2dice.mirai.bean.cards

import org.co2dice.mirai.bean.cards.api.EffectAPI
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.game.Scene

data class Situation(
    val scene: Scene,
    val cards: Cards,
    val character: CharacterCard,
    val effect: EffectAPI<Scene, Cards, CharacterCard>
){

}
