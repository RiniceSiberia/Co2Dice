package org.co2dice.mirai.core.bean.dice.entry

import org.co2dice.mirai.core.bean.dice.SampleSpace

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-13-19:52
 * @Message: Have a good time!  :)
 **/
sealed class IntSampleSpace(list : List<Int>) : SampleSpace<Int>(list) {
    init {
        if (list.isEmpty()){
            throw IllegalArgumentException("列表不能为空")
        }
    }
    open val max: Int = list.maxOrNull()!!
    open val min: Int = list.minOrNull()!!

}