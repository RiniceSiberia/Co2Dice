package org.co2dice.mirai.core.bean.dice.entry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-12-21:40
 * @Message: 常数骰
 **/
class DesignatedDice(value : Int) : DispersedSpace<Int>(mapOf(value to 1)){

    override fun toString(): String {
        return max().toString()
    }
}