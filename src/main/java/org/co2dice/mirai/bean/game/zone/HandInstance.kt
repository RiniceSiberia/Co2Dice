package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.instance.card.CardInstance
import org.co2dice.mirai.bean.game.prototype.character.Chess
import java.util.function.Predicate

class HandInstance (override var holder: Chess?, override val cards: MutableList<CardInstance<Any?>> = mutableListOf()) : ZoneInstance{


    fun discard(f:Predicate<CardInstance<Any?>>):Boolean{
        return cards.removeAll(cards.stream().filter(f::test).toList())
    }//弃掉符合条件的卡




}