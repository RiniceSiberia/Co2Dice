package org.co2dice.mirai.core.utils.situation

import org.co2dice.mirai.core.bean.api.agent.ActivatedAgent
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.bean.activated.EffectTargets
import org.co2dice.mirai.core.bean.activated.entry.EffectEntry
import org.co2dice.mirai.core.bean.game.zone.instance.Scene
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

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
    override val activatedAgent: ActivatedAgent<*>,
    override val initiator: ChessmanInstance?,
    override val target: EffectTargets,
    override val effect: EffectEntry<*>,
    override val isActive : Boolean
) : ActivationSituation(input, scene, player, activatedAgent, initiator, target, effect, isActive) {
}