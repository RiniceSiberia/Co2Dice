package org.co2dice.mirai.bean.chessman.entry

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.bean.chessman.prototype.Chessman
import org.co2dice.mirai.bean.chessman.prototype.PlayerChessman

class ChessmanEntry(
    val chessman: Chessman,
) {

    fun toInstance (holder: Player? = null) : ChessmanInstance {
        when(chessman){
            is PlayerChessman -> return ChessmanInstance(this,holder)
        }
        throw Exception("ChessmanEntry toInstance() error")
    }

}