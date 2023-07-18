package org.co2dice.mirai.core.bean.chessman.prototype

import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.attribute.table.AttributeTable
import org.co2dice.mirai.core.bean.category.CategoryPack
import org.co2dice.mirai.core.bean.dice.entry.DispersedSpace
import org.co2dice.mirai.core.bean.dice.instance.SingleDices.d6
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.ActivatedAbility
import org.co2dice.mirai.core.bean.effect.static_ability.prototype.StaticAbility
import org.co2dice.mirai.core.bean.effect.triggered_ability.prototype.TriggeredAbility
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-03-06-1:13
 * {@code @Message:} 人类棋子
 * 除了有基础的属性盘，还拥有chaos和order属性
 **/
open class HumanChessman (
    uuid: UUID,
    override val chaos: Int,
    override val order: Int,
    attributeTable : AttributeTable,
    types : CategoryPack,
    name:String,
    dice : DispersedSpace<Int> = d6,
    activatedAbilities: List<ActivatedAbility>,
    staticAbilities : List<StaticAbility>,
    triggeredAbilities: List<TriggeredAbility>,
) : Chessman(uuid,attributeTable,types,name,dice,activatedAbilities, staticAbilities,triggeredAbilities),CAO {
        //精英怪棋子，带有一些属性，但不一定是完整的六维属性
        //可以被玩家召唤，但无法作为主将

}