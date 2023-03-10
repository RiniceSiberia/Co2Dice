package org.co2dice.mirai.bean.dice.diceList

import org.co2dice.mirai.bean.dice.DiceResult
import org.co2dice.mirai.bean.dice.single.CoC.CoCReRollDice
import org.co2dice.mirai.bean.dice.single.ConstantDice
import org.co2dice.mirai.bean.dice.single.Dice
import org.co2dice.mirai.bean.dice.utils.Expect
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

/**
 * @author DUELIST
 * @date 2022/12/8 15:16
 * @input
 * @return_value
 * @message 骰子组，可以存放多个骰子，并使用封装好的批处理方法。注：如果要使用可随意修改参数的骰子，应该使用mutableDiceList
 * @log /
 */
open class DiceList(
    private val diceList: List<Dice>
    //其实是可变的，但无法访问变的方法
    ) {


    constructor(vararg diceList: Dice) : this(
        diceList = mutableListOf(*diceList)
    )

    @SafeVarargs
    constructor(vararg diceList: List<Dice>) :this(
        diceList = diceList.flatMap { it.toList() }.toMutableList()
    )

    constructor(value: Int) : this(
        diceList = mutableListOf(ConstantDice(value))
    )

    open fun roll(): DiceResult {
        var temp: DiceResult
        var bpNum = diceList.stream().filter { d: Dice -> d is CoCReRollDice }.mapToInt { obj: Dice -> obj.diceTime }
            .sum()
        //惩罚骰子和奖励骰子的抵消
        var result = DiceResult(this)
        while (bpNum != 0) {
            temp = DiceResult(this)
            result = if (bpNum > 0) {
                bpNum--
                if (result.getResult() > temp.getResult()) result else temp
            } else {
                bpNum++
                if (result.getResult() < temp.getResult()) result else temp
            }
        }
        return result
    }

    open fun expected(): Map<Int, Double>{
            val map = Expect.getExcept(this)
            val result: MutableMap<Int, Double> = HashMap(map.size)
            val sum = map.values.stream().mapToInt { obj: Int -> obj }.sum()
            for ((key, value) in map) {
                result[key] = value.toDouble() / sum.toDouble()
            }
            return result
        }

    fun getDiceByIndex(index: Int): Dice {
        return diceList[index]
    }

    open fun getDiceList(): List<Dice> {
        return diceList
    }

    open fun getMax(): Int {
        return diceList.stream().mapToInt { obj: Dice -> obj.diceMax }.sum()
    }

    open fun getMin(): Int {
        return diceList.stream().mapToInt { obj: Dice -> obj.diceMin }.sum()
    }

    fun getDiceTime(): Int {
        return diceList.stream().mapToInt { obj: Dice -> obj.diceTime }.sum()
    }

    fun getDiceNumArray(): List<List<Int>> {
        return diceList.stream().map { obj: Dice -> obj.diceNumArray }
            .collect(Collectors.toList())
    }

    override fun toString(): String {
        //将dice根据string出来的结果进行分割，相同的骰子合并，在前面加个骰子数量
        val normal = diceList.stream().filter { d: Dice -> d !is ConstantDice }
            .map { obj: Dice -> obj.toString() }
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entries.stream().map { obj: Map.Entry<String, Long> -> obj.value.toString() + obj.key }
            .collect(Collectors.joining("+"))
        var c = if (diceList.stream().anyMatch { d: Dice -> d is ConstantDice }) diceList.stream()
            .filter { d: Dice -> d is ConstantDice }
            .mapToInt { obj: Dice -> obj.diceValue }.sum().toString() else ""
        if (diceList.stream().filter { d: Dice -> d is ConstantDice }.mapToInt { obj: Dice -> obj.diceValue }
                .sum() > 0) {
            c = "+$c"
        }
        return normal + c
    }
}