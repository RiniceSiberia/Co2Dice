package org.co2dice.mirai.core.bean.dice.entry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-12-22:05
 * @Message: 抽奖池，根据传入的List<Intelligence>，随机抽取一个数值
 **/
class LotteryPool (map : Map<Int,Int>)
    : DispersedSpace<Int>(map) {

    constructor(list : List<Int>) : this(
        list.groupingBy { it }.eachCount()
    )

    override fun toString(): String {
        return "LotteryPool:[${this.toList().joinToString(",")}]"
    }
}