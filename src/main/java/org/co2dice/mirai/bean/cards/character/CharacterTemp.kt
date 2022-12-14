package org.co2dice.mirai.bean.cards.character

import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.Deck
import org.co2dice.mirai.bean.cards.Hand
import org.co2dice.mirai.bean.cards.item.ItemCard
import org.co2dice.mirai.bean.cards.effect.Effect
import org.co2dice.mirai.bean.tokens.TokenPool
import java.util.*

class CharacterTemp(
    override val id: UUID = UUID.randomUUID(),
    override var name: String = "测试用角色",
    override var flavorText: String = "用于测试混乱和秩序值，或者投骰子，请勿放于真实游戏中",
    override var imgUrl: String = "e:/",
    override var characterHolder: Player? = null,
    override val effects: MutableSet<Effect> = mutableSetOf(),
    override val items: MutableMap<ItemCard, Int> = mutableMapOf()
) : CharacterCard() {
    override val tokens: TokenPool = TokenPool(this)
    override val deck: Deck = Deck("${id}.deck","${name}的卡组", CardType.CHARACTER, mutableListOf())
    override val hand: Hand = Hand(this, mutableListOf())
    override fun deserialize(): JsonObject {
        TODO("Not yet implemented")
    }

    override fun serialize(jo: JsonObject) {
    }

}