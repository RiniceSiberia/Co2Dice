package org.co2dice.mirai.bean.game


import org.co2dice.mirai.bean.game.prototype.character.PlayerChess
import org.co2dice.mirai.bean.game.zone.DeckInstance
import org.co2dice.mirai.bean.game.zone.HandInstance

/**
  * @author 韩左券
  * @date 2022/12/8 8:51
  * @input
  * @return_value
  * @message 战斗类，用于存储战斗信息
  *
  */
class Battle(decks:MutableMap<PlayerChess, DeckInstance>,
             hasEnded: Boolean = false,
             isClosed: Boolean = false)
    : Scene(decks, hasEnded, isClosed) {
        val hands:MutableMap<PlayerChess,HandInstance> = mutableMapOf()



}