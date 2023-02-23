package org.co2dice.mirai.bean


import org.co2dice.mirai.bean.cards.character.PlayerCharacterCard
import org.co2dice.mirai.bean.game.entry.chess.ChessEntry
import org.co2dice.mirai.bean.game.instance.chess.ChessInstance
import org.co2dice.mirai.bean.game.prototype.character.PlayerChess
import org.co2dice.mirai.bean.game.zone.DeckInstance
import org.co2dice.mirai.bean.game.zone.ZoneInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:45
 * @Message: Have a good time!  :)
 **/
class Player(
    val qq: Long,val characters:Map<ChessInstance<ChessEntry<PlayerChess>>,DeckInstance>) {
    val battleField : ZoneInstance
}