package org.co2dice.mirai.utils

import com.alibaba.fastjson2.JSONObject

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-09-12:41
 * @Message: Have a good time!  :)
 **/
interface SerializeJSON {
    fun deserialize(): JSONObject
    fun serialize(jo: JSONObject)
}