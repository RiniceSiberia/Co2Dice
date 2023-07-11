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
 * @Author: DUELIST
 * @Time:  2023-05-11-22:22
 * @Message: 用于提供对象的指针，发动选择目标前的situation
 * 主要是遍历用的，用于检查是否可以发动，比如:手里有一张需要力量才能使用的牌，此时就需要使用这个来检验能否发动
 * 泛型Agent,代表了这个情景是谁发动的
 **/
open class PreActivationSituation(
    override val scene : Scene,
    override val player : PlayerInstance,
    override val agent : Agent,
    //效果启动者
    //记了和没记一样,鬼才分得清是卡是棋子是角色还是啥
    val initiator: ChessmanInstance?,
    ) : SituationApi{

    fun toActivationSituation(
        target: EffectTargets,
        effect: EffectEntry<*>,
        isActive: Boolean
    ): ActivationSituation {
        return ActivationSituation(
            input,
            scene,
            player,
            activatedAgent,
            initiator,
            target,
            effect,
            isActive
        )
    }


}