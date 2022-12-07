package org.co2dice.mirai.bean.cards

import kotlinx.serialization.json.JsonObject

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
interface Cards {
    val id: String
    var name:String
    var order:Int
    var chaos:Int
    var flavorText: String
    var imgUrl: String
    val type : CardType
    fun deserialize():JsonObject
    fun serialize(jo:JsonObject)
}