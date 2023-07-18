package org.co2dice.mirai.core.bean.dice.instance

import org.co2dice.mirai.core.bean.dice.entry.DispersedSpace

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-13-22:30
 * {@code @Message:} Have a good time!  :)
 **/
object SingleDices {
    val coin = DispersedSpace(mapOf(2 to 1))
    val d3 = DispersedSpace(mapOf(3 to 1))
    val d4 = DispersedSpace(mapOf(4 to 1))
    val d5 = DispersedSpace(mapOf(5 to 1))
    val d6 = DispersedSpace(mapOf(6 to 1))
    val d8 = DispersedSpace(mapOf(8 to 1))
    val d10 = DispersedSpace(mapOf(10 to 1))
    val d12 = DispersedSpace(mapOf(12 to 1))
    val d20 = DispersedSpace(mapOf(20 to 1))
    val d100 = DispersedSpace(mapOf(100 to 1))
    val dInfinite = DispersedSpace(mapOf(Int.MAX_VALUE to 1))

}