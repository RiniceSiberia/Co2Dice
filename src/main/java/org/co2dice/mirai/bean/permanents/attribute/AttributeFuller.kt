package org.co2dice.mirai.bean.permanents.attribute

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-23:23
 * @Message: Have a good time!  :)
 **/
class AttributeFuller (attributePoint:AbstractAttributePoint, value:Int){
    init {
        attributePoint.value += value
    }
    val attributePoints : MutableList<AbstractAttributePoint> = mutableListOf<AbstractAttributePoint>(
        for (i in 1..value) attributePoint
    )
}