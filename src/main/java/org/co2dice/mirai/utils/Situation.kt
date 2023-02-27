package org.co2dice.mirai.utils


import org.co2dice.mirai.bean.card.instance.CardInstance
import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.API.EffectAPI
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.bean.effect.EffectTargetSet

data class Situation(
    val scene: Scene,
    //进行action的场景
    val cards: CardInstance,
    //进行action的目标卡
    val chessman : ChessmanInstance<*>?,
    //进行action的目标角色，角色控制卡
    val target : EffectTargetSet,
    //action的目标
    val effect : EffectAPI
    //action本身
){

}
