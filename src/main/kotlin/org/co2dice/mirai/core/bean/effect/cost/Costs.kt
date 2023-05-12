package org.co2dice.mirai.core.bean.effect.cost

import org.co2dice.mirai.core.bean.attribute.table.AttributeEntryTable
import org.co2dice.mirai.core.bean.card.instance.item.ItemCardInstance
import org.co2dice.mirai.core.bean.card.instance.unpublic.UnPublicCardInstance
import org.co2dice.mirai.core.utils.situation.ActivationSituation

data class Costs(
    val attributes : AttributeEntryTable,
    //消耗的属性
    val tap : Set<ItemCardInstance>,
    //横置的卡片
    val sacrifice : Set<ItemCardInstance>,
    //解放的道具卡
    val discard : Set<UnPublicCardInstance>,
    //弃掉的牌
    val resource : Int,
    //花费的资源

) {
    //支付的属性
    fun pay(situation: ActivationSituation) : Boolean{
        //消耗掉消耗的属性,如果属性不够，那么返回false
        situation.initiator?.attributeTable?.payCost(attributes) ?: return false
        //横置卡片
        tap.forEach { it.tap = true }
        //解放卡片,

        //弃牌

        //花掉资源
        situation.player.resource -= this.resource
    }

    fun combine(other : Costs) : Costs {
        val new =  Costs(
            attributes = attributes.combine(other.attributes),
            tap = tap + other.tap,
            sacrifice = sacrifice + other.sacrifice,
            discard = discard + other.discard,
            resource = resource + other.resource
        )
        //如果tap,sacrifice等参数的数量和传入的对不上，那么就会报错
        if (new.tap.size != tap.size + other.tap.size) throw Exception("tap size error")
        if (new.sacrifice.size != sacrifice.size + other.sacrifice.size) throw Exception("sacrifice size error")
        if (new.discard.size != discard.size + other.discard.size) throw Exception("discard size error")
        return new
    }
}