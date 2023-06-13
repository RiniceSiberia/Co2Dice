package org.co2dice.mirai.core.bean.dice.entry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-13:04
 * @Message: Have a good time!  :)
 **/
class CheatDice(
    realField : List<Int>,
    override val min: Int = 1,
    override val max :Int = realField.maxOrNull()!!
    //假的上下限
) : IntSampleSpace(realField) {

    override fun roll(): Int {
        //从Min到max(包括min和max)中选择一个随机数
        return (min..max).random()
    }

    override fun toString(): String {
        return "D$max"
    }
}