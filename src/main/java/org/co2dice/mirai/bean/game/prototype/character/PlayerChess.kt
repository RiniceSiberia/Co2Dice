package org.co2dice.mirai.bean.game.prototype.character

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.dice.DiceList
import org.co2dice.mirai.bean.dice.NormalDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-23:06
 * @Message: Have a good time!  :)
 **/
open class PlayerChess(
    str: Int = DiceList(NormalDice(6),NormalDice(6),NormalDice(6)).roll().getResult(),
    con: Int = DiceList(NormalDice(6),NormalDice(6),NormalDice(6)).roll().getResult(),
    dex: Int = DiceList(NormalDice(6),NormalDice(6),NormalDice(6)).roll().getResult(),
    wis: Int = DiceList(NormalDice(6),NormalDice(6),NormalDice(6)).roll().getResult(),
    int: Int = DiceList(NormalDice(6),NormalDice(6),NormalDice(6)).roll().getResult(),
    san: Int = DiceList(NormalDice(6),NormalDice(6),NormalDice(6)).roll().getResult(),
    name: String
) : Chess(str, con, dex, wis, int, san, name) {



}