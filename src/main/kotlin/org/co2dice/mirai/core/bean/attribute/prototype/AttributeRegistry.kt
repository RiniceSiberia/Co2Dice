package org.co2dice.mirai.core.bean.attribute.prototype

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-11-15:21
 * @Message: Have a good time!  :)
 **/
object AttributeRegistry {
    val eliteAttribute : List<Attribute> = listOf(
        Strength,Constitution,Dexterity,Wisdom,Intelligence,Sanity
    )

    val mobAttribute : List<Attribute> = listOf(
        Loyalty
    )

    val customAttribute : List<Attribute> = mutableListOf()
    //自定义属性

    fun getAttribute(name : String) : Attribute?{
        return eliteAttribute.find { it.nameStr == name } ?: mobAttribute.find { it.nameStr == name }
    }
}