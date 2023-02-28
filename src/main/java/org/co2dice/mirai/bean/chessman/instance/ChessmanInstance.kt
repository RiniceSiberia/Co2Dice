package org.co2dice.mirai.bean.chessman.instance

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.API.SetTokenAPI
import org.co2dice.mirai.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.bean.counter.CounterPool

abstract class ChessmanInstance(
    val entry : ChessmanEntry,
    var holder: Player?
) : SetTokenAPI {
    val counterPool: CounterPool
        = CounterPool(
        entry.chessman.str,
        entry.chessman.con,
        entry.chessman.dex,
        entry.chessman.wis,
        entry.chessman.int,
        entry.chessman.san)

    fun die() {}


}