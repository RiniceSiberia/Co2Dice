package org.co2dice.mirai.bean

import com.alibaba.fastjson2.JSONObject
import org.co2dice.mirai.utils.SerializeJSON

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:45
 * @Message: Have a good time!  :)
 **/
class Player(val qq: Long) :SerializeJSON{
    override fun deserialize(): JSONObject {
        TODO("Not yet implemented")
    }

    override fun serialize(jo: JSONObject) {
        TODO("Not yet implemented")
    }
}