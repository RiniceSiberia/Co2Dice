package org.co2dice.mirai.bean.game.instance.chessman

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.api.SetTokenAPI
import org.co2dice.mirai.bean.game.entry.chessman.ChessmanEntry
import org.co2dice.mirai.bean.tokens.TokenPool

class ChessmanInstance(
    val entry : ChessmanEntry,
    var holder: Player?
) : SetTokenAPI {
    val tokenPool: TokenPool
        = TokenPool(
        entry.chessman.str,
        entry.chessman.con,
        entry.chessman.dex,
        entry.chessman.wis,
        entry.chessman.int,
        entry.chessman.san)

}