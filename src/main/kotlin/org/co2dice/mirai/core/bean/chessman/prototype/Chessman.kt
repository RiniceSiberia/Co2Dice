package org.co2dice.mirai.core.bean.chessman.prototype

import org.co2dice.mirai.core.bean.api.PrototypeStructure
import org.co2dice.mirai.core.bean.attribute.table.AttributeTable
import org.co2dice.mirai.core.bean.category.CategoryPack
import org.co2dice.mirai.core.bean.dice.entry.DispersedSpace
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.ActivatedAbility
import org.co2dice.mirai.core.bean.effect.static_ability.prototype.StaticAbility
import org.co2dice.mirai.core.bean.effect.triggered_ability.prototype.TriggeredAbility
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:22
 * @Message: 棋子基础类
 **/
sealed class Chessman(
    override val uuid: UUID,
    val attributeTable : AttributeTable,
    val types : CategoryPack,
    val name: String,
    val dice : DispersedSpace<Int>,
    //属性骰
    open val activatedAbilities: List<ActivatedAbility>,
    open val staticAbilities : List<StaticAbility>,
    open val triggeredAbilities : List<TriggeredAbility>,
) : PrototypeStructure{
    //棋子是有属性的，但这个属性不会存储在抽象的棋子里。不同的棋子有不同的属性，比如杂鱼有忠诚,人类有六维，dnd有法力(专属)
}