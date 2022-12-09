package org.co2dice.mirai.utils

import org.co2dice.mirai.bean.dice.Dice
import org.co2dice.mirai.bean.dice.DiceList
import org.co2dice.mirai.bean.dice.MutableDiceList
import org.co2dice.mirai.bean.dice.UsuallyDices
import java.util.*
import kotlin.math.abs

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
        val exceptList = mutableListOf<Double>()
        var f = true
        while (f){
            for (dice in getListByPriority()){
                //获取当前的遍历数
                dices.mutable.add(dice.dice)
                val odds:Map<Int,Double> = dices.expected
                //概率
                val min = dices.mutableMin
                val max = dices.mutableMax
                for (i in min..max){
                    exceptList[dice.priority] = fun(): Any {
                        return if (odds[i] != null) {
                            odds[i]!! * i + exceptList[dice.priority]
                        } else {
                            exceptList[dice.priority]
                        }
                    }.invoke() as Double
                }
                dices.mutable.remove(dice.dice)
            }
            //现在，exList中承载了权重从高到低，每个权重下的期望值，需要从中
            val resultList = mutableListOf<Dice>()
            //获取到了所有的期望值
            for (i in 0 until exceptList.size){
                if (exceptList[i] > e - 0.5 && exceptList[i] < e + 0.5){
                    resultList.add(getListByPriority()[i].dice)
                }
            }
            //现在，resultList中承载了所有的期望值在e+-0.5之间的骰子
            //如果resultList为空，则说明没有找到，将概率最接近的骰子加入到dices中
            if (resultList.isEmpty()){
                //将和期望值最接近的骰子丢进去，注意，不是最大的骰子，而是最接近的骰子
                dices.diceList.add(getListByPriority()[exceptList.indexOf(exceptList.stream().sorted(
                    Comparator.comparingDouble { abs(it - e) } ).findFirst().get())].dice)
            }else{
                //如果不为空，则获取resultList的第一个元素，丢入dices中，结束循环
                dices.diceList.add(resultList[0])
                f = false
            }
        }
        return dices
    }

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