package org.co2dice.mirai.core.bean.dice.entry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-12-21:40
 * @Message: Have a good time!  :)
 **/
class DesignatedDice(value : Int) : IntSampleSpace(listOf(value)){

    override fun toString(): String {
        return max.toString()
    }
}