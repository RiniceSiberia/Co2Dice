package org.co2dice.mirai.bean.cards.character

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.cards.CardType
import org.co2dice.mirai.bean.cards.CardsInstance
import org.co2dice.mirai.bean.cards.effect.Effect
import org.co2dice.mirai.bean.tokens.TokenPool

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-21:22
 * @Message: Have a good time!  :)
 **/
abstract class CharacterCard : CardsInstance() {
    override val type = CardType.CHARACTER
    abstract var characterHolder:Player?
    abstract val effects : MutableSet<Effect>
    abstract val tokens : TokenPool
    //属性和token池子
}