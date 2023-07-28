package org.co2dice.mirai.core.utils.situation

import org.co2dice.mirai.core.bean.api.agent.ActivatedAgent
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.bean.activated.EffectTargets
import org.co2dice.mirai.core.bean.activated.entry.EffectEntry
import org.co2dice.mirai.core.bean.api.agent.Agent
import org.co2dice.mirai.core.bean.game.zone.instance.Scene
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-11-22:22
 * {@code @Message:} 用于提供对象的指针，选择目标确认发动后，支付cost时,自排连锁时的situation
 **/
open class ActivationSituation(
    val costIndex : Int,
    //cost选择的index
    val targetIndex : Int,
    //使用targetIndex选择的目标
    scene : Scene,
    player : PlayerInstance,
    agent : Agent,
    initiator: List<ChessmanInstance>,
    //效果的发动棋子，用于提供属性加值，一旦发动就不能变更,即使棋子中途死亡也会正常加值
    isActive : Boolean,
)  : PreActivationSituation() {
}