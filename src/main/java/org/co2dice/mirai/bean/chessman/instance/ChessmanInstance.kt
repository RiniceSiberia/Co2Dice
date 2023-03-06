package org.co2dice.mirai.bean.chessman.instance

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.API.SetTokenAPI
import org.co2dice.mirai.bean.chessman.attribute.AttributeInstanceTable
import org.co2dice.mirai.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.bean.counter.CounterPool
import org.co2dice.mirai.bean.game.Damage

abstract class ChessmanInstance(
    val entry : ChessmanEntry,
    var holder: Player?,
    val attributeInstanceTable: AttributeInstanceTable = entry.chessman.attributeEntryTable.toInstance()
) : SetTokenAPI {

    override val counterPool: CounterPool = CounterPool()

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