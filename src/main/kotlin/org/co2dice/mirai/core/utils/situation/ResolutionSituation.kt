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
 * @Time:  2023-05-11-22:24
 * @Message: 用于提供对象的指针，结算前的situation
 **/
data class ResolutionSituation(
    override val input: Map<String, Any>,
    override val scene: Scene,
    override val player: PlayerInstance,
    override val agent: Agent<*>,
    override val initiator: ChessmanInstance?,
    override val target: EffectTargets,
    override val effect: EffectEntry<*>,
    override val isActive : Boolean
) : ActivationSituation(input, scene, player, agent, initiator, target, effect, isActive) {
}