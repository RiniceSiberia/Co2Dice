package org.co2dice.mirai.core.bean.card.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.api.agent.ActivatedAgent
import org.co2dice.mirai.core.bean.api.agent.StaticAgent
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.GyActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.static_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.static_ability.instance.GyStaticAbilityInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-15-23:45
 * {@code @Message:} 墓地发动的效果
 **/
@Serializable
class GYCardInstance (
    override val entry: CardEntry,
    override var holder: PlayerInstance,
    override var activatedAbilities: List<GyActivatedAbilityInstance> = entry.activatedAbilityEntries.toInstance(),
    override val staticAbilities: List<GyStaticAbilityInstance> = entry.staticAbilityEntries.toInstance(),
) : PublicCardInstance(),
    ActivatedAgent<GyActivatedAbilityInstance>,
    StaticAgent<GyStaticAbilityInstance>,
    DependPlayer {
}