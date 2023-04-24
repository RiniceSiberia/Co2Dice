package org.co2dice.mirai.core.publicEnums

import org.co2dice.mirai.core.bean.dice.single.DesignatedDice
import org.co2dice.mirai.core.bean.dice.single.api.AbstractDice
import org.co2dice.mirai.core.bean.dice.single.FairDice

enum class UsuallyDices(val diceList: AbstractDice, val priority :Int) {
    D1(DesignatedDice(1),2),
    COIN(FairDice(2),1),
    D3(FairDice(3),6),
    D4(FairDice(4),7),
    D5(FairDice(5),5),
    D6(FairDice(6),11),
    D8(FairDice(8),8),
    D10(FairDice(10),9),
    D12(FairDice(12),10),
    D20(FairDice(20),12),
    NEGATIVE_1(DesignatedDice(-1),4),
    ;



}