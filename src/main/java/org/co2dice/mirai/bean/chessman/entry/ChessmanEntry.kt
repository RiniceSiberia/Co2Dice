package org.co2dice.mirai.bean.chessman.entry

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.bean.chessman.instance.EliteChessmanInstance
import org.co2dice.mirai.bean.chessman.prototype.Chessman
import org.co2dice.mirai.bean.chessman.prototype.EliteChessman

class ChessmanEntry(
    val chessman: Chessman<*>,
) {

    fun toInstance (holder: Player? = null) : ChessmanInstance {
        //根据T的种类，返回不同的ChessmanInstance
        if (chessman is EliteChessman) {
            return EliteChessmanInstance(this,holder)
        }
        throw Exception("ChessmanEntry toInstance() error")
    }

}