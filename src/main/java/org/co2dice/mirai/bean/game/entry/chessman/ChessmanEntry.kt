package org.co2dice.mirai.bean.game.entry.chessman

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.instance.chessman.ChessmanInstance
import org.co2dice.mirai.bean.game.prototype.chessman.Chessman
import org.co2dice.mirai.bean.game.prototype.chessman.PlayerChessman

class ChessmanEntry(
    val chessman: Chessman,
) {

    fun toInstance (holder: Player? = null) : ChessmanInstance{
        when(chessman){
            is PlayerChessman  -> return ChessmanInstance(this,holder)
        }
        throw Exception("ChessmanEntry toInstance() error")
    }

}