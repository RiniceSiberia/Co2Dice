package org.co2dice.mirai.core.bean.effect.cost

import org.co2dice.mirai.core.bean.attribute.table.AttributeEntryTable
import org.co2dice.mirai.core.bean.card.instance.item.ItemCardInstance
import org.co2dice.mirai.core.bean.card.instance.unpublic.UnPublicCardInstance
import org.co2dice.mirai.core.bean.effect.utils.Situation

data class Costs(
    private val attributes : AttributeEntryTable,
    //消耗的属性
    private val tap : Set<ItemCardInstance>,
    //横置的卡片数量
    private val sacrifice : Set<ItemCardInstance>,
    //解放的道具卡数量
    private val discard : Set<UnPublicCardInstance>,
    //弃掉的牌的数量
    private val resource : Int,
    //花费的资源

) {
    //支付的属性
    fun pay(situation: Situation) : Boolean{
        //消耗掉消耗的属性,如果属性不够，那么返回false
        situation.initiator?.attributeTable?.payCost(attributes) ?: return false
        //横置卡片
        tap.forEach { it.tap = true }
        //解放卡片,

        //弃牌

        //花掉资源
        situation.player.resource -= this.resource
    }
}