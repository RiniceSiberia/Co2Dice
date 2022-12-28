package org.co2dice.mirai.bean

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:45
 * @Message: Have a good time!  :)
 **/
@Serializable
class Player(
    @SerialName("qq")
    val qq: Long) {

}