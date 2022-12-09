package org.co2dice.mirai.utils

import org.co2dice.mirai.bean.dice.Dice
import org.co2dice.mirai.bean.dice.DiceList
import org.co2dice.mirai.bean.dice.MutableDiceList
import org.co2dice.mirai.bean.dice.UsuallyDices
import java.util.*
import kotlin.math.abs
import kotlin.streams.toList

object DiceUtils {
    val EXPECTED_VALUE:List<Double> = listOf(
        2.0,
        3.0,
        4.5,
        6.0,
        8.0,
        10.0,
        12.5,
        15.0,
        18.0,
        21.0,
        25.0,
        29.0,
        34.0,
        39.5,
        45.5,
        52.0,
        59.5,
        68.0,
        77.0,
        87.0,
        98.0
    )
    fun getExpectDice(e:Double): DiceList {
        val dices = MutableDiceList(mutableListOf(),mutableListOf())
        //循环遍历将所有的骰子都加入到dices,计算出所有的可能性，再将dices清空，算出最接近期望值的样子
        var exceptList = mutableListOf<Temp>()
        //优先级和骰子的映射和期望值
        var f = true
        while (f){
            for (dice in getListByPriority()){
                //计算dices中所有骰子的期望值
                dices.mutable.add(dice.dice)
                val odds:Map<Int,Double> = dices.expected
                //所有和的概率
                //获取一个总的期望值，即每个key乘以每个value后得到的总和
                var value:Double = 0.0;
                for (u in odds){
                    value += u.key * u.value
                }
                value /= odds.size
                //向excpetList中添加一个临时对象，然后清空dices
                exceptList.add(Temp(dice.dice,dice.priority,value))
                dices.mutable.remove(dice.dice)
            }
            //现在，exList中承载了权重从高到低，每个权重下的期望值以及对应的骰子
            val resultList = mutableListOf<Temp>()
            for (i in 0 until exceptList.size){
                //对所有骰子进行遍历，找出最接近期望值的骰子
                if (exceptList[i].value > e - 0.5 && exceptList[i].value < e + 0.5){
                    //如果骰子的期望值在e+-0.5的范围内，那么就将这个骰子加入到resultList中
                    resultList.add(exceptList[i])
                }
            }
            //现在，resultList中承载了所有的期望值在e+-0.5之间的骰子
            //如果resultList为空，则说明没有找到，将概率最接近的骰子加入到dices中
            if (resultList.isEmpty()){
                //将和期望值最接近的骰子丢进去，注意，不是最大的骰子，而是最接近的骰子)
                resultList.add(exceptList.stream().sorted(Comparator.comparingDouble<Temp?> {
                    return@comparingDouble abs(it.value - e)
                }.reversed()).findFirst().get())
//                dices.diceList.add(getListByPriority()[exceptList.indexOf(exceptList.stream().sorted(
//                    Comparator.comparingDouble { abs(it - e) } ).findFirst().get())].dice)
            }else{
                //如果不为空，则获取一个期望值最高的元素，丢入dices中，结束循环
                val r :List<Temp> = resultList.stream().sorted(
                    Comparator.comparingInt<Temp?> { it.priority }).toList()
                r.reversed()
                dices.diceList.add(r[0].dice)
                f = false
            }
        }
        return dices
    }

    private class Temp(var dice: Dice, var priority: Int, var value: Double)

    /**
     * @author 韩左券
     * @date 2022/12/9 10:29
     * @input
     * @return_value
     * @message 将数据从大到小根据权重排序
     * @log /
     */
    fun getListByPriority(): List<UsuallyDices> {
        return UsuallyDices.values().sortedByDescending { it.priority }
    }

}