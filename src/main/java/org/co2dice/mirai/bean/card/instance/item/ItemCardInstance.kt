package org.co2dice.mirai.bean.card.instance.item

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.card.instance.CardInstance
import org.co2dice.mirai.bean.api.DependPlayer
import org.co2dice.mirai.bean.card.api.OpenCardInstance
import org.co2dice.mirai.bean.card.entry.CardEntry
import org.co2dice.mirai.bean.card.prototype.ItemCard
import org.co2dice.mirai.publicEnums.ItemType

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
    override val entry: CardEntry,
    override var holder: Player? = null,
    //持有者可为空
    val occupy : MutableMap<ItemType,Int>
    = if (entry.card is ItemCard) entry.card.occupy.toMutableMap()
    else mutableMapOf(ItemType.MISCELLANEOUS to 1),
    //默认值为，如果entry里的卡是装备卡，就读取其占用的装备栏，否则就是丢入杂项栏
    override val uniqueId: Int
) : CardInstance(entry),OpenCardInstance, DependPlayer {


}