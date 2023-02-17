package org.co2dice.mirai.bean.game.instance.card


import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.game.instance.api.EffectAPI
import org.co2dice.mirai.bean.game.instance.character.CharacterCard
import org.co2dice.mirai.bean.game.instance.effect.EffectTargetSet

data class Situation(
    val scene: Scene,
    val cards: CardsInstance,
    val character : CharacterCard,
    val target : EffectTargetSet,
    val effect : EffectAPI<Scene, CardsInstance, CharacterCard>
){

}
