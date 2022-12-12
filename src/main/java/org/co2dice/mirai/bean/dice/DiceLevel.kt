package org.co2dice.mirai.bean.dice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-23:59
 * @Message: 这是一个基于费用产生出的骰子伤害模型。注:其中的伤害都是加上了1回合的价值的，所以需要慎重计算伤害。
 **/
enum class DiceLevel (val cost:Int,val diceList : DiceList){
    COST0 (0, DiceList(NormalDice(3))),
    COST1 (1, DiceList(NormalDice(6))),
    COST2 (2, DiceList(NormalDice(8))),
    COST3 (3, DiceList(NormalDice(12))),
    COST4 (4, DiceList(NormalDice(12),NormalDice(3))),
    COST5 (5, DiceList(NormalDice(20))),
    COST6 (6, DiceList(NormalDice(20),NormalDice(4))),
    COST7 (7, DiceList(NormalDice(20),NormalDice(8))),
    COST8 (8, DiceList(NormalDice(20),NormalDice(12),ConstantDice(1))),
    COST9 (9, DiceList(NormalDice(20),NormalDice(20))),
    COST10 (10, DiceList(NormalDice(20),NormalDice(20),NormalDice(6))),
    COST11 (11, DiceList(NormalDice(20),NormalDice(20),NormalDice(12),NormalDice(3))),
    COST12 (12, DiceList(NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(4))),
    COST13 (13, DiceList(NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(12),NormalDice(3))),
    COST14 (14, DiceList(NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(6))),
    COST15 (15, DiceList(NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20))),
    COST16 (16, DiceList(NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(12))),
    COST17 (17, DiceList(NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(10))),
    COST18 (18, DiceList(NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20),NormalDice(20))),
    ;
    fun getDiceListByCost (cost:Int): DiceList? {
        var diceList:DiceList? = null
        for ( i in 0..values().size){
            if (values()[i].cost == cost){
                diceList = values()[i].diceList
            }
        }
        return diceList
    }
}
