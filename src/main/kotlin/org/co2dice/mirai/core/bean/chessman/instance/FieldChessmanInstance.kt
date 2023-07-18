package org.co2dice.mirai.core.bean.chessman.instance

import org.co2dice.mirai.core.bean.api.AttributeAPI
import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.api.PermanentInstance
import org.co2dice.mirai.core.bean.attribute.table.AttributeInstanceTable
import org.co2dice.mirai.core.bean.attribute.table.AttributeTable
import org.co2dice.mirai.core.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.core.bean.effect.activated_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.activated_ability.instance.FieldActivatedAbilityInstance
import org.co2dice.mirai.core.bean.effect.static_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.static_ability.instance.FieldStaticAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.entry.toInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.EnterFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.LeavingFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.effect.triggered_ability.instance.OnFieldTriggeredAbilityInstance
import org.co2dice.mirai.core.bean.game.Damage
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry
import org.co2dice.mirai.core.utils.situation.PreActivationSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-03-06-15:46
 * {@code @Message:} 场上的棋子实例
 **/
open class FieldChessmanInstance(
    entry: ChessmanEntry,
    holder: PlayerInstance,
    override val chaos: Int? = entry.chaos,
    override val order: Int? = entry.order,
    attributeTable: AttributeInstanceTable = entry.attributeEntry.toInstance(),
    override val activatedAbilities: List<FieldActivatedAbilityInstance> = entry.activatedAbilityEntries.toInstance(),
    override val staticAbilities: List<FieldStaticAbilityInstance> = entry.staticAbilityEntries.toInstance(),
    override val enterFieldTriggeredAbilities: List<EnterFieldTriggeredAbilityInstance>
    = entry.triggeredAbilityEntries.toInstance(),
    override val leavingFieldTriggeredAbilities: List<LeavingFieldTriggeredAbilityInstance>
    = entry.triggeredAbilityEntries.toInstance(),
    override val onFieldTriggeredAbilities: List<OnFieldTriggeredAbilityInstance>
    = entry.triggeredAbilityEntries.toInstance(),
    registry : UniqueIdRegistry,
): ChessmanInstance(
    entry = entry,
    holder = holder,
    attributeTable = attributeTable,
    chaos = chaos,
    order = order), AttributeAPI,PermanentInstance, CAO {

    override val uniqueId: Int = registry.register(this :: class)



    fun die() {
        isAlive = false
        disengagement()
    }

    fun disengagement(){
        //当任何一个属性归零时调用，战斗不能状态
        isActive = false
    }

    fun enterFieldEvent(situation : PreActivationSituation){

    }

    fun leavingFieldEvent(situation: PreActivationSituation){

    }
    fun payAttributeCost(table : AttributeTable) : Boolean{
        return attributeTable.payCost(table) && survive()
    }

    fun survive() : Boolean{
        return if (attributeTable.survive()){
            true
        }else{
            disengagement()
            false
        }
    }

    fun makeDamage(damage: Damage): Boolean {
        if (attributeTable.contain(damage.damageType)) {
            attributeTable.subtractionValue(damage.damage.roll().open(),damage.damageType)
            return true
        }
        return false
    }
    //有属性打对应属性，没属性返回失败
}