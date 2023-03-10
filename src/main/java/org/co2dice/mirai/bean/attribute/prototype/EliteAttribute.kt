package org.co2dice.mirai.bean.attribute.prototype

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-05-17:29
 * @Message: Have a good time!  :)
 **/
enum class EliteAttribute (override val id:String, override val nameStr:String) : AttributeAPI {
    STR("str","力量"),
    CON("con","体质"),
    DEX("dex","敏捷"),
    WIS("wis","感知"),
    INT("int","智力"),
    SAN("san","理智")
    ;
}