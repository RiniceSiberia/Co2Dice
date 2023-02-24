package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.instance.card.CardInstance
import java.util.function.Predicate

class HandInstanceStack (override var holder: Player?,
                         override val cards: MutableList<CardInstance> = mutableListOf()) : StackZoneInstance{


    fun discard(f:Predicate<CardInstance>):Boolean{
        return cards.removeAll(cards.stream().filter(f::test).toList())
    }//弃掉符合条件的卡




}