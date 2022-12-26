package org.co2dice.mirai.bean.cards

import com.alibaba.fastjson2.annotation.JSONField
import org.co2dice.mirai.utils.SerializeJSON
import java.util.UUID

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: Have a good time!  :)
 **/
abstract class Cards {
    @JSONField(name = "cardId")
    abstract val cardId: UUID
    @JSONField(name = "cardName")
    abstract var cardName:String
    @JSONField(name = "flavorText")
    abstract val flavorText: String
    @JSONField(name = "imgUrl")
    var imgUrl: String = ""
    @JSONField(name = "cardType")
    abstract val type : CardType
    @JSONField(name = "faceUp")
    var faceUp : Boolean = false
    @JSONField(name = "tap")
    var tap : Boolean = false
}