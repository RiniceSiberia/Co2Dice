package org.co2dice.mirai.core.bean.dice.entry

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-12-21:40
 * {@code @Message:} 常数骰
 **/
class DesignatedDice(value : Int) : DispersedSpace<Int>(mapOf(value to 1)){

    override fun toString(): String {
        return max().toString()
    }
}