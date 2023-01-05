package org.co2dice.mirai.bean.game.objective

import org.co2dice.mirai.bean.cards.effect.Effect
/**
  * @author DUELIST
  * @date 2023/1/4 10:01
  * @input
  * @return_value
  * @message 这是以卡片的效果为对象的静态对象，而不是卡片效果发动时的动态对象
  * @log /
  */
class EffectObjective(val effect: Effect) : TargetObjective<Effect> {
    override fun getTarget(): Effect {
        return effect
    }

    override fun legal(): Boolean {
        TODO("Not yet implemented")
    }

    override fun process(): Boolean {
        TODO("Not yet implemented")
    }
}