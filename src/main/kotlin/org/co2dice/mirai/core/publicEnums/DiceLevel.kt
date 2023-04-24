package org.co2dice.mirai.core.publicEnums

import org.co2dice.mirai.core.bean.dice.diceList.DiceList
import org.co2dice.mirai.core.bean.dice.single.DesignatedDice
import org.co2dice.mirai.core.bean.dice.single.FairDice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-23:59
 * @Message: 这是一个基于费用产生出的骰子伤害模型。注:其中的伤害都是加上了1回合的价值的，所以需要慎重计算伤害。
 **/
enum class DiceLevel (val cost:Int,val diceList : DiceList){
    COST0 (0, DiceList(FairDice(3))),
    COST1 (1, DiceList(FairDice(6))),
    COST2 (2, DiceList(FairDice(8))),
    COST3 (3, DiceList(FairDice(12))),
    COST4 (4, DiceList(
        FairDice(12),
        FairDice(3)
    )
    ),
    COST5 (5, DiceList(FairDice(20))),
    COST6 (6, DiceList(
        FairDice(20),
        FairDice(4)
    )
    ),
    COST7 (7, DiceList(
        FairDice(20),
        FairDice(8)
    )
    ),
    COST8 (8, DiceList(
        FairDice(20),
        FairDice(12),
        DesignatedDice(1)
    )
    ),
    COST9 (9, DiceList(
        FairDice(20),
        FairDice(20)
    )
    ),
    COST10 (10, DiceList(
        FairDice(20),
        FairDice(20),
        FairDice(6)
    )
    ),
    COST11 (11, DiceList(
        FairDice(20),
        FairDice(20),
        FairDice(12),
        FairDice(3)
    )
    ),
    COST12 (12, DiceList(
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(4)
    )
    ),
    COST13 (13, DiceList(
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(12),
        FairDice(3)
    )
    ),
    COST14 (14, DiceList(
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(6)
    )
    ),
    COST15 (15, DiceList(
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20)
    )
    ),
    COST16 (16, DiceList(
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(12)
    )
    ),
    COST17 (17, DiceList(
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(10)
    )
    ),
    COST18 (18, DiceList(
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20),
        FairDice(20)
    )
    ),
    ;

    companion object{
        @JvmStatic
        fun getDiceListByCost (cost:Int): DiceList? {
            var diceList: DiceList? = null
            for ( i in 0..values().size){
                if (values()[i].cost == cost){
                    diceList = values()[i].diceList
                }
            }
            return diceList
        }
    }
}
