package org.co2dice.mirai.bean.permanents

import org.co2dice.mirai.bean.permanents.attribute.AbstractAttributePoint

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-23:23
 * @Message: Have a good time!  :)
 **/
class PermanentsFuller (point: AbstractAttributePoint, value:Int){
    init {

    }
    val points : MutableList<AbstractAttributePoint> = mutableListOf<AbstractAttributePoint>()
        .forEach()
    var value = value


    fun timeFlow(){
        points.forEach{
            if (it.isTemp()){
                it.lifeTime--
                if (it.lifeTime == 0){
                    points.remove(it)
                }
            }
        }
    }
}