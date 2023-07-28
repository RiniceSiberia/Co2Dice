package org.co2dice.mirai.core.bean.card.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.prototype.ItemCard
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.EnterFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.situation.ResolutionSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-02-19:51
 * {@code @Message:} 咒语卡，只在释放时存在
 * 存在本身就是这个卡的cost和发动条件检查已经通过了，发动通过才会出现这么一张卡
 **/
@Serializable
class SpellCardInstance(
    override val entry: CardEntry,
    override var holder: PlayerInstance,
    val spellAbilities : List<EnterFieldTriggeredAbilityInstance> = entry.triggeredAbilityEntries.toInstance(),
) : PublicCardInstance(),DependPlayer {

    fun settlement(situation: ResolutionSituation) : Boolean{
        //结算
        spellAbilities.forEach { it.invoke(situation) }
        //效果结算完后，是装备就将其移到装备区，是咒语就将其丢到墓地
        if (entry.prototype is ItemCard && situation.initiator != null){
            situation.scene.equip(situation.player,situation.initiator,
                ItemCardInstance(
                entry = entry,
                registry = situation.getRegistry(),
                holder = situation.player))
        }
        return situation.getGy().addCard(GYCardInstance(entry,situation.player))
    }


}