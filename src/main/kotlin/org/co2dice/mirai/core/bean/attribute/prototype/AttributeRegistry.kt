package org.co2dice.mirai.core.bean.attribute.prototype

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-11-15:21
 * {@code @Message:} Have a good time!  :)
 **/
object AttributeRegistry {
    fun eliteAttribute() : List<Attribute> = listOf(
        Strength,Constitution,Dexterity,Wisdom,Intelligence,Sanity
    )

    val customAttribute : List<Attribute> = mutableListOf()
    //自定义属性

    fun getAttribute(name : String) : Attribute?{
        return eliteAttribute().find { it.nameStr == name }
            ?: customAttribute.find { it.nameStr == name }
    }
}