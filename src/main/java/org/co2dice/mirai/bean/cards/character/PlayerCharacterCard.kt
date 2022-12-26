package org.co2dice.mirai.bean.cards.character

import com.alibaba.fastjson2.JSONObject
import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Deck
import org.co2dice.mirai.bean.cards.Hand
import org.co2dice.mirai.bean.cards.item.ItemCard
import org.co2dice.mirai.bean.cards.effect.Effect
import org.co2dice.mirai.bean.tokens.TokenPool
import java.util.UUID

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-23:06
 * @Message: Have a good time!  :)
 **/
class PlayerCharacterCard(
    override val id: UUID = UUID.randomUUID(),
    override var name: String,
    override var flavorText: String,
    override var imgUrl: String,
    override var characterHolder: Player?,
) : CharacterCard() {
    override var tokens = TokenPool( this ).addRandomHumanFuller()
    override val deck: Deck = Deck("${id}.deck","${name}的卡组",CardType.CHARACTER, mutableListOf())
    override val hand: Hand = Hand(this, mutableListOf())


    override val effects: MutableSet<Effect> = mutableSetOf()
    override val items: MutableMap<ItemCard, Int> = mutableMapOf()

    fun reloadTokens() {
        tokens = TokenPool( holder = this).addRandomHumanFuller()
    }

    override fun deserialize(): JSONObject {
        TODO("Not yet implemented")
    }

    override fun serialize(jo: JSONObject) {

    }

}