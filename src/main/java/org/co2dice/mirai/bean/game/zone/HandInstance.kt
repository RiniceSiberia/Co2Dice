package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.game.instance.card.CardsInstance
import org.co2dice.mirai.bean.game.instance.character.CharacterCard
import java.util.function.Predicate

class HandInstance (override var holder: CharacterCard?, override val cards: MutableList<CardsInstance> = mutableListOf()) : ZoneInstance{


    fun discard(f:Predicate<CardsInstance>):Boolean{
        return cards.removeAll(cards.stream().filter(f::test).toList())
    }//弃掉符合条件的卡




}