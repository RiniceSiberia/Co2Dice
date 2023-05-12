package org.co2dice.mirai.core.bean.chessman.instance

import org.co2dice.mirai.core.bean.api.Agent
import org.co2dice.mirai.core.bean.api.AttributeAPI
import org.co2dice.mirai.core.bean.attribute.table.AttributeInstanceTable
import org.co2dice.mirai.core.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.core.bean.effect.prototype.field.FieldEffect
import org.co2dice.mirai.core.bean.game.Damage
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.decorator.handler.DecoratorHolder

abstract class ChessmanInstance(
    val entry : ChessmanEntry,
    var holder: PlayerInstance?,
    override val attributeTable: AttributeInstanceTable = entry.chessman.attributeEntryTable.toInstance()
) : AttributeAPI , Agent<FieldEffect> , DecoratorHolder() {


    var isAlive = true

    var isActive = false



    fun die() {
        isAlive = false
        disengagement()
    }

    fun disengagement(){
        //当任何一个属性归零时调用，战斗不能状态
        isActive = false
    }


    abstract fun makeDamage(damage: Damage) : Boolean





}