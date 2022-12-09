package org.co2dice.mirai.bean.dice

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-23:59
 * @Message: 这是一个基于费用产生出的骰子伤害模型。注:其中的伤害都是加上了1回合的价值的，所以需要慎重计算伤害。
 **/
enum class DiceLevel (val cost:Int,val diceList : DiceList){


}
enum class UsuallyDices(val dice: Dice,val priority :Int) {
    D1(ConstantDice(1),6),
    COIN(NormalDice(2),5),
    D3(NormalDice(3),3),
    D4(NormalDice(4),7),
    D5(NormalDice(5),2),
    D6(NormalDice(6),11),
    D8(NormalDice(8),8),
    D10(NormalDice(10),9),
    D12(NormalDice(12),10),
    D20(NormalDice(20),12),
    D100(NormalDice(100),13),
}