package org.co2dice.mirai.core.bean.dice.single

import org.co2dice.mirai.core.bean.dice.single.api.AbstractDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-12-22:05
 * @Message: 抽奖池，根据传入的List<Int>，随机抽取一个数值
 **/
class LotteryPool (private val pool : List<Int>): AbstractDice() {
    override val max: Int
        get() = pool.maxOrNull() ?: 0
    override val min: Int
        get() = pool.minOrNull() ?: 0

    override fun toList(): List<Int> {
        return pool
    }

    override fun roll(): Int {
        return pool.random()
    }

    override fun toString(): String {
        return "pool:[${pool.joinToString(",")}]"
    }
}