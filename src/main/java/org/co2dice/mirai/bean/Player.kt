package org.co2dice.mirai.bean


import org.co2dice.mirai.bean.cards.character.PlayerCharacterCard
import org.co2dice.mirai.bean.game.zone.DeckInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:45
 * @Message: Have a good time!  :)
 **/
class Player(
    val qq: Long,val characters:Map<PlayerCharacterCard,DeckInstance>) {
}