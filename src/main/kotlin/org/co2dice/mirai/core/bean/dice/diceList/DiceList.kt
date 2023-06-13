package org.co2dice.mirai.core.bean.dice.diceList

import org.co2dice.mirai.core.bean.dice.DiceResult
import org.co2dice.mirai.core.bean.dice.entry.DesignatedDice
import org.co2dice.mirai.core.bean.dice.entry.AbstractDice
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors
import kotlin.collections.ArrayList

/**
 * @author DUELIST
 * @date 2022/12/8 15:16
 * @input
 * @return_value
 * @message 骰子组，可以存放多个骰子，并使用封装好的批处理方法。注：如果要使用可随意修改参数的骰子，应该使用mutableDiceList
 * @log /
 */
class DiceList(
    diceList: List<AbstractDice> = mutableListOf()
    //其实是可变的，但无法访问变的方法
    ) : ArrayList<AbstractDice>(diceList){

    constructor(vararg diceList: AbstractDice) : this(mutableListOf(*diceList))

    @SafeVarargs
    constructor(vararg diceList: List<AbstractDice>) :this(diceList.flatMap { it.toList() }.toMutableList())

    constructor(value: Int) : this(mutableListOf(DesignatedDice(value)))
    //批处理
    fun roll(): DiceResult {
        var temp: DiceResult
        var bpNum = this.stream().filter { d: AbstractDice -> d is CoCReRollDice }.mapToInt { obj: AbstractDice -> obj.diceTime }
            .sum()
        //惩罚骰子和奖励骰子的抵消
        var result = DiceResult(this)
        while (bpNum != 0) {
            temp = DiceResult(this)
            result = if (bpNum > 0) {
                bpNum--
                if (result.open() > temp.open()) result else temp
            } else {
                bpNum++
                if (result.open() < temp.open()) result else temp
            }
        }
        return result
    }

    //获取期望值
    fun expectedInt(): Map<Int, Int> {
        //获取所有骰子的array
        val arrays: List<IntArray> = this.stream().map { obj: AbstractDice -> obj.diceNumArray.toIntArray() }.toList()
        return calculatePossibleResults(arrays)
    }

    fun exceptedDouble() : Map<Int,Double>{
        val intExcept = expectedInt()
        val sum = intExcept.values.stream().mapToInt(Int::toInt).sum()
        return intExcept.mapValues { it.value.toDouble() / sum }
    }

    private fun integrate() {
        //合并常数
        var c = 0
        for (d in this) {
            if (d is DesignatedDice) {
                c += d.getDiceValue()
                remove(d)
            }
        }
        if (c != 0) {
            add(DesignatedDice(c))
        }
    }

    fun getMax(): Int {
        return this.stream().mapToInt { obj: AbstractDice -> obj.diceMax }.sum()
    }

    fun getMin(): Int {
        return this.stream().mapToInt { obj: AbstractDice -> obj.diceMin }.sum()
    }

    fun getDiceTime(): Int {
        return this.stream().mapToInt { obj: AbstractDice -> obj.diceTime }.sum()
    }

    fun getDiceNumArray(): List<List<Int>> {
        return this.stream().map { obj: AbstractDice -> obj.diceNumArray }
            .collect(Collectors.toList())
    }

    override fun toString(): String {
        //将dice根据string出来的结果进行分割，相同的骰子合并，在前面加个骰子数量
        val normal = this.stream().filter { d: AbstractDice -> d !is DesignatedDice }
            .map { obj: AbstractDice -> obj.toString() }
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entries.stream().map { obj: Map.Entry<String, Long> -> obj.value.toString() + obj.key }
            .collect(Collectors.joining("+"))
        var c = if (this.stream().anyMatch { d: AbstractDice -> d is DesignatedDice }) this.stream()
            .filter { d: AbstractDice -> d is DesignatedDice }
            .mapToInt { obj: AbstractDice -> obj.diceValue }.sum().toString() else ""
        if (this.stream().filter { d: AbstractDice -> d is DesignatedDice }.mapToInt { obj: AbstractDice -> obj.diceValue }
                .sum() > 0) {
            c = "+$c"
        }
        return normal + c
    }



    override fun add(element: AbstractDice): Boolean {
        return super.add(element).apply { integrate() }
    }

    override fun add(index: Int, element: AbstractDice) {
        super.add(index, element).apply { integrate() }
    }

    override fun addAll(elements: Collection<AbstractDice>): Boolean {
        return super.addAll(elements).apply { integrate() }
    }

    override fun addAll(index: Int, elements: Collection<AbstractDice>): Boolean {
        return super.addAll(index, elements).apply { integrate() }
    }

    override fun set(index: Int, element: AbstractDice): AbstractDice {
        return super.set(index, element).apply { integrate() }
    }

    override fun sort(c: Comparator<in AbstractDice>?) {
        integrate()
        super.sort(c)
    }

    companion object{
        fun calculatePossibleResults(arrays: List<IntArray>): Map<Int, Int> {
            val results = mutableMapOf<Int, Int>()
            calculatePossibleResults(arrays, 0, 0, results)
            return results
        }

        private fun calculatePossibleResults(
            arrays: List<IntArray>,
            arrayIndex: Int,
            sum: Int,
            results: MutableMap<Int, Int>
        ) {
            if (arrayIndex == arrays.size) {
                // 当数组索引达到数组的长度时，我们已经从每个数组中选择了一个数字，现在可以将结果添加到结果列表中
                results.merge(sum, 1) { a, b -> a + b }
                return
            }

            // 从当前数组中遍历所有数字，并递归调用calculatePossibleResults()函数以选择下一个数组中的数字
            val currentArray = arrays[arrayIndex]
            val totalElements = currentArray.size
            for (i in 0 until totalElements) {
                val newSum = sum + currentArray[i]
                calculatePossibleResults(arrays, arrayIndex + 1, newSum, results)
            }
        }

    }

}