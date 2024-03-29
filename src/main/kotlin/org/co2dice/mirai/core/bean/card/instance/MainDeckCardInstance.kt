package org.co2dice.mirai.core.bean.card.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.api.agent.SpellAgent
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.EnterFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-14-20:26
 * {@code @Message:} 非公开的类，手牌和主卡组的通用类(不包括场地)
 * 无法持有静态效果，但可以变成一个action(行动)，比如发动技能或者放置
 **/
@Serializable
class MainDeckCardInstance (
    override val entry: CardEntry,
    override var holder: PlayerInstance,
    override val spellAbilities : List<EnterFieldTriggeredAbilityInstance> = entry.triggeredAbilityEntries.toInstance(),
    ) : CardInstance(), SpellAgent, DependPlayer {
        var open : Boolean = false

    fun launchConditions(situation: PreActivationSituation) : Boolean{
        //如果是技能牌,检查其发动条件
    }
    fun cost() : Boolean{

    }

}