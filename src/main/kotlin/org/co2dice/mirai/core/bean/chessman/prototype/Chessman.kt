package org.co2dice.mirai.core.bean.chessman.prototype

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.PrototypeStructure
import org.co2dice.mirai.core.bean.attribute.table.AttributeTable
import org.co2dice.mirai.core.bean.category.CategoryPack
import org.co2dice.mirai.core.bean.effect.activated_ability.prototype.ActivatedAbility
import org.co2dice.mirai.core.bean.effect.static_ability.prototype.StaticAbility
import org.co2dice.mirai.core.bean.effect.triggered_ability.prototype.TriggeredAbility

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2022-12-06-21:22
 * {@code @Message:} 棋子基础类
 **/
@Serializable
sealed class Chessman() : PrototypeStructure {
    abstract val attributeTable : AttributeTable
    abstract val types : CategoryPack
    abstract val name: String
    abstract val dice : Map<Int,Int>
    //属性骰
    abstract val activatedAbilities: List<ActivatedAbility>
    abstract val staticAbilities : List<StaticAbility>
    abstract val triggeredAbilities : List<TriggeredAbility>
    //棋子是有属性的，但这个属性不会存储在抽象的棋子里。不同的棋子有不同的属性，比如杂鱼有忠诚,人类有六维，dnd有法力(专属)
}