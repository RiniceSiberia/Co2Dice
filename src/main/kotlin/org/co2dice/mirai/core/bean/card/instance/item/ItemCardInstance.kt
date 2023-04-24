package org.co2dice.mirai.core.bean.card.instance.item

import org.co2dice.mirai.core.bean.api.DependPlayer
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.api.PermanentCardInstance
import org.co2dice.mirai.core.bean.card.prototype.ItemCard
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.instance.EffectInstance
import org.co2dice.mirai.core.bean.effect.prototype.field.FieldActiveEffect
import org.co2dice.mirai.core.bean.effect.prototype.field.FieldPassiveEffect
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
    override var holder: PlayerInstance? = null,
    val occupy : MutableMap<ItemType,Int>
    = if (entry.card is ItemCard) entry.card.occupy.toMutableMap() else mutableMapOf(),
    //占用槽，默认值为，如果entry里的卡是装备卡，就读取其占用的装备栏，否则就留空
    entryRound : Int,

    override var activeEffects: EffectInstance<FieldActiveEffect> = ,
    override var passiveEffects: EffectInstance<FieldPassiveEffect> =
) : PermanentCardInstance<FieldActiveEffect,FieldPassiveEffect>(entry,registry,entryRound),DependPlayer<PlayerInstance?> {


}