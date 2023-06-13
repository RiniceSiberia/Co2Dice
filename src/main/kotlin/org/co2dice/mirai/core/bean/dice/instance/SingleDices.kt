package org.co2dice.mirai.core.bean.dice.instance

import org.co2dice.mirai.core.bean.dice.entry.FairDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-13-22:30
 * @Message: Have a good time!  :)
 **/
object SingleDices {
    val coin = FairDice(2)
    val d3 = FairDice(3)
    val d4 = FairDice(4)
    val d5 = FairDice(5)
    val d6 = FairDice(6)
    val d8 = FairDice(8)
    val d10 = FairDice(10)
    val d12 = FairDice(12)
    val d20 = FairDice(20)
    val d100 = FairDice(100)
    val dInfinite = FairDice(Int.MAX_VALUE)

}