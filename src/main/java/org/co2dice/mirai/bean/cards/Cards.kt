package org.co2dice.mirai.bean.cards

import kotlinx.serialization.json.JsonObject

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
interface Cards {
    fun getName(): String
    fun getFlavorText(): String
    fun getImg(): String
    fun getType():CardType
    fun deserialize():JsonObject
    fun serialize(jo:JsonObject)
}