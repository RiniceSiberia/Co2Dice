package org.co2dice.mirai.core.bean.dice.single.api

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-12-21:37
 * @Message: Have a good time!  :)
 **/
abstract class AbstractDice() {
    abstract val max: Int
    abstract val min: Int

    abstract fun toList(): List<Int>
    abstract fun roll(): Int

}