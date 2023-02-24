package org.co2dice.mirai.bean


import org.co2dice.mirai.bean.game.entry.chess.ChessEntry
import org.co2dice.mirai.bean.game.instance.chess.ChessInstance
import org.co2dice.mirai.bean.game.prototype.character.PlayerChessman
import org.co2dice.mirai.bean.game.zone.DeckInstanceStack
import org.co2dice.mirai.bean.game.zone.StackZoneInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:45
 * @Message: Have a good time!  :)
 **/
class Player(
    val qq: Long,val characters:Map<ChessInstance<ChessEntry<PlayerChessman>>,DeckInstanceStack>) {
    val battleField : StackZoneInstance
}