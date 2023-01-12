package org.co2dice.mirai.bean.game

import org.co2dice.mirai.bean.cards.character.CharacterCard

/**
  * @author 韩左券
  * @date 2022/12/8 8:51
  * @input
  * @return_value
  * @message 战斗类，用于存储战斗信息
  *
  */
class Battle(characters: MutableList<CharacterCard>,
             damageList: MutableList<Damage> = mutableListOf(),
             hasEnded: Boolean = false,
             isClosed: Boolean = false)
    : Scene(characters, damageList, hasEnded, isClosed) {





}