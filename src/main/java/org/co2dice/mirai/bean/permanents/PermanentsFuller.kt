package org.co2dice.mirai.bean.permanents

import org.co2dice.mirai.bean.permanents.attribute.AbstractAttributePoint

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-23:23
 * @Message: Have a good time!  :)
 **/
class PermanentsFuller (point: AbstractAttributePoint,
                        var value: Int
){
    init {
    }
    //重复添加n个point进points中
    val points : MutableList<AbstractAttributePoint> = mutableListOf<AbstractAttributePoint>().apply{
        for (i in 1..value){
            add(point)
        }
    }

    fun timePointCheck(){
        points.forEach{
            if (it is TempPermanents && it.timeCheck()){
                points.remove(it)
            }
        }
    }
}