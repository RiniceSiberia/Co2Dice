package org.co2dice.mirai.bean.cards.character

import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.cards.item.ItemCard
import org.co2dice.mirai.bean.cards.skill.Skill
import org.co2dice.mirai.bean.tokens.TokenPool

class CharacterTemp(
    override val id: String = "-1",
    override var name: String = "测试用角色",
    override var flavorText: String = "用于测试混乱和秩序值，或者投骰子，请勿放于真实游戏中",
    override var imgUrl: String = "e:/",
    override var characterHolder: Player? = null,
    override val skills: MutableSet<Skill> = mutableSetOf(),
    override val items: MutableMap<ItemCard, Int> = mutableMapOf(),
    override val tokens: TokenPool = TokenPool()
) : CharacterCard() {

    override fun deserialize(): JsonObject {
        TODO("Not yet implemented")
    }

    override fun serialize(jo: JsonObject) {
    }

}