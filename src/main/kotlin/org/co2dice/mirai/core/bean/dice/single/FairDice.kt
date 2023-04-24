package org.co2dice.mirai.core.bean.dice.single

import org.co2dice.mirai.core.bean.dice.single.api.AbstractDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-12-21:40
 * @Message: Have a good time!  :)
 **/
open class FairDice(private val diceNum: Int) : AbstractDice() {
    override val max: Int
        get() = diceNum
    override val min: Int
        get() = 1

    override fun toList(): List<Int> {
        val list: MutableList<Int> = ArrayList()
        for (i in 1..diceNum) {
            list.add(i)
        }
        return list
    }

    override fun roll(): Int {
        return (Math.random() * diceNum + 1).toInt()
    }

    override fun toString(): String {
        return "D$diceNum"
    }
}