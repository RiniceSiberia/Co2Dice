package org.co2dice.mirai.core.bean.card.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.api.agent.ActivatedAgent
import org.co2dice.mirai.core.bean.api.agent.StaticAgent
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.BanishActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.static_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.static_ability.instance.BanishStaticAbilityInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-27-23:18
 * {@code @Message:} Have a good time!  :)
 **/
@Serializable
class BanishCardInstance(
    override val entry: CardEntry,
    override var holder: PlayerInstance,
    override val activatedAbilities: List<BanishActivatedAbilityInstance> = entry.activatedAbilityEntries.toInstance(),
    override val staticAbilities: List<BanishStaticAbilityInstance> = entry.staticAbilityEntries.toInstance(),
) : PublicCardInstance(),
    ActivatedAgent<BanishActivatedAbilityInstance>,
    StaticAgent<BanishStaticAbilityInstance>,
    DependPlayer {
}