package org.co2dice.mirai.bean.cards

import kotlinx.serialization.json.JsonObject

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
abstract class Cards {
    abstract val id: String
    abstract var name:String
    abstract var order:Int
    abstract var chaos:Int
    abstract var flavorText: String
    abstract var imgUrl: String
    abstract val type : CardType
    abstract fun deserialize():JsonObject
    abstract fun serialize(jo:JsonObject)
}