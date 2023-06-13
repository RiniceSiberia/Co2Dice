package org.co2dice.mirai.core.bean.dice.entry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-12-21:40
 * @Message: Have a good time!  :)
 **/
open class FairDice(private val diceNum: Int) : IntSampleSpace((1..diceNum).toList()) {

    init {
        if (diceNum < 2) {
            throw IllegalArgumentException("普通骰子上限不能小于2")
        }
    }

    override fun roll(): Int {
        return (Math.random() * diceNum + 1).toInt()
    }

    override fun toString(): String {
        return "D${max}"
    }
}