package org.co2dice.mirai.core.bean.dice.single

import org.co2dice.mirai.core.bean.dice.single.api.AbstractDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-12-21:40
 * @Message: Have a good time!  :)
 **/
class DesignatedDice(private val diceValue : Int) : AbstractDice() {
    override val max: Int
        get() = diceValue
    override val min: Int
        get() = diceValue


    override fun toList(): List<Int> {
        return listOf(diceValue)
    }

    override fun roll(): Int {
        return diceValue
    }

    override fun toString(): String {
        return diceValue.toString()
    }
}