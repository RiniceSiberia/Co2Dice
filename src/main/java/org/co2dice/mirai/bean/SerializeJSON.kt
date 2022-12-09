package org.co2dice.mirai.bean

import kotlinx.serialization.json.JsonObject

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-09-12:41
 * @Message: Have a good time!  :)
 **/
interface SerializeJSON {
    abstract fun deserialize(): JsonObject
    abstract fun serialize(jo: JsonObject)
}