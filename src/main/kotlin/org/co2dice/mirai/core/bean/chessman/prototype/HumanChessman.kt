package org.co2dice.mirai.core.bean.chessman.prototype

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.attribute.table.AttributeTable
import org.co2dice.mirai.core.bean.category.CategoryPack
import org.co2dice.mirai.core.bean.dice.instance.SingleDices.d6
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.ActivatedAbility
import org.co2dice.mirai.core.bean.effect.static_ability.prototype.StaticAbility
import org.co2dice.mirai.core.bean.effect.triggered_ability.prototype.TriggeredAbility
import org.co2dice.mirai.core.utils.serializer.UUIDSerializer
import java.util.*

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-03-06-1:13
 * {@code @Message:} 人类棋子
 * 除了有基础的属性盘，还拥有chaos和order属性
 **/
@Serializable
@SerialName("human_chessman")
class HumanChessman (
    @Serializable(with = UUIDSerializer::class)
    override val uuid: UUID = UUID.randomUUID(),
    override val chaos: Int,
    override val order: Int,
    override val attributeTable : AttributeTable,
    override val types : CategoryPack,
    override val name:String,
    override val dice : Map<Int,Int> = d6,
    override val activatedAbilities: List<ActivatedAbility>,
    override val staticAbilities : List<StaticAbility>,
    override val triggeredAbilities: List<TriggeredAbility>,
) : Chessman(),CAO {
        //精英怪棋子，带有一些属性，但不一定是完整的六维属性
        //可以被玩家召唤，但无法作为主将

}