package org.co2dice.mirai.bean.game.gameInstance.card


import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.game.gameInstance.card.api.EffectAPI
import org.co2dice.mirai.bean.game.gameInstance.card.character.CharacterCard
import org.co2dice.mirai.bean.game.gameInstance.card.effect.EffectTargetSet

data class Situation(
    val scene: Scene,
    val cards: CardsInstance,
    val character : CharacterCard,
    val target : EffectTargetSet,
    val effect : EffectAPI<Scene, CardsInstance, CharacterCard>
){

}
