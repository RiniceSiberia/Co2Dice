package org.co2dice.mirai.bean.cards

import org.co2dice.mirai.bean.SerializeJSON
import java.util.UUID

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
abstract class Cards : SerializeJSON {
    abstract val id: UUID
    abstract var name:String
    abstract var flavorText: String
    abstract var imgUrl: String
    abstract val type : CardType
    var faceUp : Boolean = false
    var tap : Boolean = false
}