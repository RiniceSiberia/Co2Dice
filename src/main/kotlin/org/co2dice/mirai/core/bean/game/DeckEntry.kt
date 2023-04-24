package org.co2dice.mirai.core.bean.game

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.core.bean.game.zone.DeckInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-18-23:27
 * @Message: Have a good time!  :)
 **/
class DeckEntry(
    val commander : ChessmanEntry,
    val holder: PlayerInstance,
    val cards: MutableList<CardEntry>) {

}