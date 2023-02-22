package org.co2dice.mirai.bean.game.instance.card


import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.game.api.EffectAPI
import org.co2dice.mirai.bean.game.instance.chess.ChessInstance
import org.co2dice.mirai.bean.game.prototype.character.Chess
import org.co2dice.mirai.bean.game.prototype.effect.EffectTargetSet

data class Situation<CA :CardInstance<*>,CH : ChessInstance<*>>(
    val scene: Scene,
    //进行action的场景
    val cards: CA,
    //进行action的目标卡
    val chess : CH?,
    //进行action的目标角色，角色控制卡
    val target : EffectTargetSet,
    //action的目标
    val effect : EffectAPI
    //action本身
){

}
