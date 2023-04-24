package org.co2dice.mirai.core.bean.chessman.entry

import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.bean.chessman.instance.EliteChessmanInstance
import org.co2dice.mirai.core.bean.chessman.instance.MobChessmanInstance
import org.co2dice.mirai.core.bean.chessman.prototype.Chessman
import org.co2dice.mirai.core.bean.chessman.prototype.EliteChessman
import org.co2dice.mirai.core.bean.chessman.prototype.MobChessman
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

class ChessmanEntry(
    val chessman: Chessman,
) {

    fun toInstance (holder: PlayerInstance? = null) : ChessmanInstance {
        //根据chessman的种类，返回不同的ChessmanInstance
        if (chessman is EliteChessman) {
            return EliteChessmanInstance(this,holder)
        }
        if (chessman is MobChessman){
            return MobChessmanInstance(this,holder)
        }
        throw Exception("ChessmanEntry toInstance() error")
    }

}