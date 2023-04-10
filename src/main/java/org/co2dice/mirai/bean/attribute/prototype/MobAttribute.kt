package org.co2dice.mirai.bean.attribute.prototype

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-06-15:40
 * @Message: Have a good time!  :)
 **/
enum class MobAttribute(override val id:String, override val nameStr:String) : AttributeAPI {
    LOYALTY("loyalty","忠诚度"),
    ;

    override fun toString(): String {
        return id
    }

    companion object{
        fun findByName(name:String) : EliteAttribute? {
            return EliteAttribute.values().find { it.nameStr == name }
        }
    }
}