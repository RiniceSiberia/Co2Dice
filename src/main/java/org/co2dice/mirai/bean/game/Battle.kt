package org.co2dice.mirai.bean.game

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.morph.Morph

/**
  * @author 韩左券
  * @date 2022/12/8 8:51
  * @input
  * @return_value
  * @message 战斗类，用于存储战斗信息
  *
  */
class Battle<M : Morph>(override val players: MutableList<Player>) : Scene(players) {
    val morphs : List<M> = mutableListOf()




}