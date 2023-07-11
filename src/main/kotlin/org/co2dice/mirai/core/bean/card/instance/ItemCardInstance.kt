package org.co2dice.mirai.core.bean.card.instance

import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.prototype.ItemCard
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.FieldActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.static_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.static_ability.instance.FieldStaticAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.EnterFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.LeavingFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.OnFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.publicEnums.ItemType
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
  * @author trasgl
  * @date 2022/12/6 9:16
  * @input 
  * @return_value
  * @message 道具卡实例，所有装备卡发动后都依赖于角色，不存在没有角色依赖的装备卡。
  * 装备卡对应的角色消失后会全部进入墓地，对应的意象是装备掉落在战场上了。敏捷属性的技能可以捡起这些装备。
  * 而装备如果损坏，则对应的是banish，放逐，这种情况下就必须通过智力或者意志，要么将其修复，要么将其复制。
  * @log /
  */
class ItemCardInstance(
    entry: CardEntry,
    registry : UniqueIdRegistry,
    override var holder: PlayerInstance,
    val occupy : MutableMap<ItemType,Int> = if (entry.prototype is ItemCard) entry.prototype.occupy.toMutableMap() else mutableMapOf(),
    //占用槽，默认值为，如果entry里的卡是装备卡，就读取其占用的装备栏，否则就留空
    entryRound : Int = 0,
    override var activatedAbilities: List<FieldActivatedAbilityInstance> = entry.activatedAbilityEntries.toInstance(),
    override val staticAbilities: List<FieldStaticAbilityInstance> = entry.staticAbilityEntries.toInstance(),
    override val enterFieldTriggeredAbilities: List<EnterFieldTriggeredAbilityInstance>
    = entry.triggeredAbilityEntries.toInstance(),
    override val leavingFieldTriggeredAbilities: List<LeavingFieldTriggeredAbilityInstance>
    = entry.triggeredAbilityEntries.toInstance(),
    override val onFieldTriggeredAbilities: List<OnFieldTriggeredAbilityInstance>
    = entry.triggeredAbilityEntries.toInstance(),
    ) : PermanentCardInstance(entry,registry,entryRound),DependPlayer {
        init {
            enterFieldTriggeredAbilities.forEach {
                //进程效果逐条触发
            }
        }
    override val chaos: Int?
        get(){
            return if (entry.prototype is CAO){
                (entry.prototype as CAO).chaos
            }else{
                null
            }
        }
    override val order: Int?
        get(){
            return if (entry.prototype is CAO){
                (entry.prototype as CAO).order
            }else{
                null
            }
        }


}