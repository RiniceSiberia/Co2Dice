package org.co2dice.mirai.bean.dice

import kotlin.math.abs

enum class UsuallyDices(val dice: Dice,val priority :Int) {
    D1(ConstantDice(1),2),
    COIN(NormalDice(2),1),
    D3(NormalDice(3),6),
    D4(NormalDice(4),7),
    D5(NormalDice(5),5),
    D6(NormalDice(6),11),
    D8(NormalDice(8),8),
    D10(NormalDice(10),9),
    D12(NormalDice(12),10),
    D20(NormalDice(20),12),
    NEGATIVE_1(ConstantDice(-1),4),
    ;



}