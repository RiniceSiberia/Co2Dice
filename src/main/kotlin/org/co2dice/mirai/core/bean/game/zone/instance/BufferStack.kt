package org.co2dice.mirai.core.bean.game.zone.instance

import org.co2dice.mirai.core.bean.action.Action
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.utils.situation.ResolutionSituation

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-07-02-20:32
 * {@code @Message:} Have a good time!  :)
 **/
class BufferStack(
    private val spells : MutableList<Pair<Action,ChessmanInstance>> = mutableListOf()
) {

    fun add(action: Action, initializer : ChessmanInstance) : Boolean{
        return spells.add(Pair(action,initializer))
    }

    fun checkOut(situation : ResolutionSituation){
        spells.forEach {
            it.first.operation(situation)
        }
        spells.clear()
    }
}