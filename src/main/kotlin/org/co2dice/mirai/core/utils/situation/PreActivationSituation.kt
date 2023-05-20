package org.co2dice.mirai.core.utils.situation

import org.co2dice.mirai.core.bean.api.Agent
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.bean.effect.EffectTargets
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.game.Scene
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.situation.api.SituationApi

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-05-11-22:22
 * @Message: 用于提供对象的指针，发动选择目标前的situation
 * 主要是遍历用的，用于检查是否可以发动，比如:手里有一张需要力量才能使用的牌，此时就需要使用这个来检验能否发动
 **/
open class PreActivationSituation(
    val input: Map<String, Any>,
    val scene: Scene,
    val player: PlayerInstance,
    val agent: Agent<*>,
    val initiator: ChessmanInstance?,

) {

    fun toActivationSituation(
        target: EffectTargets,
        effect: EffectEntry<*>,
        isActive: Boolean
    ): ActivationSituation {
        return ActivationSituation(
            input,
            scene,
            player,
            agent,
            initiator,
            target,
            effect,
            isActive
        )
    }
}