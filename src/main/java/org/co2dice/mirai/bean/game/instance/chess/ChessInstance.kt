package org.co2dice.mirai.bean.game.instance.chess

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.api.SetTokenAPI
import org.co2dice.mirai.bean.game.entry.chess.ChessEntry
import org.co2dice.mirai.bean.tokens.TokenPool

class ChessInstance<E : ChessEntry<*>>(
    val entry : E,
    var holder: Player?
) : SetTokenAPI {
    val tokenPool: TokenPool
        = TokenPool(
        entry.chess.str,
        entry.chess.con,
        entry.chess.dex,
        entry.chess.wis,
        entry.chess.int,
        entry.chess.san)

}