package org.co2dice.mirai.core.bean.dice.single

import org.co2dice.mirai.core.bean.dice.single.api.AbstractDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-13:04
 * @Message: Have a good time!  :)
 **/
class CheatDice(
    override val max: Int,
    override val min: Int,
    private val fakeValue :Int = max) : AbstractDice() {
    override fun toList(): List<Int> {
        //返回min到max
        return (min..max).toList()
    }

    override fun roll(): Int {
        //从Min到max(包括min和max)中选择一个随机数
        return (min..max).random()
    }

    override fun toString(): String {
        return "D$fakeValue"
    }
}