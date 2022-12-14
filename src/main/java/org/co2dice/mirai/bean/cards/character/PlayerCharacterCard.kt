package org.co2dice.mirai.bean.cards.character

import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.cards.item.ItemCard
import org.co2dice.mirai.bean.cards.effect.Effect
import org.co2dice.mirai.bean.tokens.TokenPool

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-23:06
 * @Message: Have a good time!  :)
 **/
class PlayerCharacterCard(
    override val id: String,
    override var name: String,
    override var flavorText: String,
    override var imgUrl: String,
    override var characterHolder: Player?,
) : CharacterCard() {
    override var tokens = TokenPool( this ).addRandomHumanFuller()
    override val effects: MutableSet<Effect> = mutableSetOf()
    override val items: MutableMap<ItemCard, Int> = mutableMapOf()

    fun reloadTokens() {
        tokens = TokenPool( holder = this).addRandomHumanFuller()
    }


    override fun deserialize(): JsonObject{
        TODO()
    //        return JsonObject()
    }
    override fun serialize(jo: JsonObject) {

    }
}