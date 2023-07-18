package org.co2dice.mirai.core.bean.dice.entry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-12-21:40
 * {@code @Message:} 普通骰子
 **/
class FairDice(diceNum: Int)
    : DispersedSpace<Int>((1..diceNum).associateWith { 1 }.toMap()) {

    init {
        if (diceNum < 2) {
            throw IllegalArgumentException("普通骰子上限不能小于2")
        }
    }

    override fun toString(): String {
        return "D${max()}"
    }
}