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
 **/
open class PreActivationSituation(
    override val input: Map<String, Any>,
    override val scene: Scene,
    override val player: PlayerInstance
) : SituationApi {

    fun toActivationSituation(
        agent: Agent<*>,
        initiator: ChessmanInstance?,
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