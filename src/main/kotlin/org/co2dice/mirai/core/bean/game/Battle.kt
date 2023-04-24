package org.co2dice.mirai.core.bean.game


import org.co2dice.mirai.core.bean.game.zone.ZoneInstanceSet
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

/**
  * @author 韩左券
  * @date 2022/12/8 8:51
  * @input
  * @return_value
  * @message 战斗类，用于存储战斗信息
  *
  */
class Battle(zones :MutableMap<PlayerInstance, ZoneInstanceSet>,
             hasEnded : Boolean = false,
             isClosed : Boolean = false)
    : Scene(zones, hasEnded, isClosed) {




}