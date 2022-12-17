package org.co2dice.mirai.bean.cards

import kotlinx.serialization.json.JsonObject
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-17-18:50
 * @Message: Have a good time!  :)
 **/
class CardBack(override val type: CardType) : Cards() {
    override val id: UUID = UUID.randomUUID()
    override var name: String = "未知卡片"
    override var flavorText: String = "NaN"
    override var imgUrl: String = ""
    override fun deserialize(): JsonObject {
        TODO("Not yet implemented")
    }

    override fun serialize(jo: JsonObject) {
    }
}