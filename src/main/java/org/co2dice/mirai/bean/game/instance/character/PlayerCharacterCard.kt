package org.co2dice.mirai.bean.game.instance.character

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.instance.effect.Effect
import org.co2dice.mirai.bean.tokens.TokenPool
import java.util.UUID

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-23:06
 * @Message: Have a good time!  :)
 **/
class PlayerCharacterCard(
    override val cardId: UUID = UUID.randomUUID(),
    override var cardName: String,
    override var flavorText: String,
    override var imgUrl: String,
    override var characterHolder: Player?,
) : CharacterCard() {
    override var tokens = TokenPool( this ).addRandomHumanFuller()

    override val effects: MutableSet<Effect> = mutableSetOf()

    fun reloadTokens() {
        tokens = TokenPool( holder = this).addRandomHumanFuller()
    }



}