package org.co2dice.mirai.core.utils

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.exp
import kotlin.math.ln


/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-24-18:22
 * {@code @Message:} Have a good time!  :)
 **/
object MathUtils {
    fun factorial(n : Int) : Long{
        var result = 1L
        for (i in 2..n) result *= i
        return result
    }

    fun combination(n: Int, k: Int): Long {
        //从n个元素中取k个元素的组合数
        return factorial(n) / (factorial(k) * factorial(n - k))
    }

    fun BigDecimal.pow(exponent: BigDecimal,precision : Int = 4,roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal {
        // 创建一个MathContext对象，默认指定4位有效数字和四舍五入模式
        val mc = MathContext(precision, roundingMode)
        // 计算base的自然对数
        val lnBase = BigDecimal(ln(this.toDouble()), mc)
        // 计算exponent乘以lnBase
        val product = exponent.multiply(lnBase, mc)
        // 计算e的product次方
        // 返回结果
        return BigDecimal(exp(product.toDouble()), mc)
    }

    /*
    * 仅为求概率而定制
    * 当介于0和pY之间时会自动停止计算
     */
//    fun definiteIntegral(
//        pmf : (BigDecimal) -> BigDecimal,
//        up : BigDecimal,
//        down : BigDecimal,
//        precisionX: Int = 4,
//        timeBreak : Int = 16777216
//        //计算次数熔断开关,16777216
//    ) : BigDecimal{
//        val dX = BigDecimal.ONE / BigDecimal.TEN.pow(precisionX)
//        var dY : BigDecimal
//        val times = ((up-down)/dX).toInt()
//        if (times >= timeBreak) throw Exception("Too many times to calculate")
//        var index : BigDecimal = down
//        var result : BigDecimal = BigDecimal.ZERO
//        while (index < up ){
//            dY = pmf(index)
//            result += dY*dX
//            index += dX
//        }
//        return result
//    }

    fun <X,Y,Z> ((X, Y) -> Z).partial(y: Y): (X) -> Z {
        return { x -> this(x, y) }
    }




    // apply simpson rule to approximately compute the integration.
    fun simpsonRule(upper: BigDecimal,
                    lower: BigDecimal,
                    n: Int,
                    df: (BigDecimal) -> BigDecimal): BigDecimal {
        //上限，下限，需要分割成的段，函数
        //默认精度200
        var result = BigDecimal.ZERO
        val unit = (upper - lower) / n.toBigDecimal()
        val factor1 = unit / BigDecimal(3)
        val x = arrayOf<BigDecimal>()
        for (i in x.indices) {
            x[i] = lower + unit * i.toBigDecimal()
        }
        for (i in x.indices) {
            result += if (i == 0 || i == x.size - 1) {
                df(x[i])
            } else if (i % 2 == 0) { // if 'i' is even num.
                BigDecimal(2) * df(x[i])
            } else { // if i is odd num.
                BigDecimal(4) * df(x[i])
            }
        }
        result *= factor1
        return result
    }

    fun <A : Any> selectElements(list: Collection<A>,
                                 conditionSet : (Set<A>) -> Boolean,
                                 conditionSeg : (A) -> Boolean,
                                 sort : (A) -> Int = {0},
                                 //排序的方法，升序排列,最前面的返回1，第二的返回2，以此类推
                                 ): List<Set<A>> {
        // 创建一个空的list作为结果
        val result = mutableListOf<Set<A>>()
        // 调用一个辅助方法，传入listA, M和一个空的set作为初始值
        helper(list, setOf(), conditionSet, conditionSeg, result)
        // 返回结果
        return result.apply {
            //对内部进行排序和去重
            forEach { it.sortedBy (sort) }
            this.distinct()
        }
    }

    fun <A> helper(list: Collection<A>,
                   temp: Set<A>,
                   conditionSet: (Set<A>) -> Boolean,
                   conditionEntry: (A) -> Boolean,
                   result: MutableList<Set<A>>) {
        // 如果list为空，说明已经遍历完所有的元素
        if (list.isEmpty()) {
            // 判断temp是否满足conditionSet函数
            if (conditionSet(temp)) {
                // 如果是，则将temp加入到结果中
                result.add(temp)
            }
        } else {
            // 否则，取出list中的第一个元素
            val first = list.first()
            // 判断它是否满足conditionEntry函数
            if (conditionEntry(first)) {
                // 如果是，则将其加入到temp中
                val newTemp = temp + first
                // 递归地调用自己，传入剩余的list和新的temp
                helper(list.drop(1), newTemp, conditionSet, conditionEntry, result)
            }
            // 无论是否满足条件，都要考虑不选这个元素的情况
            // 递归地调用自己，传入剩余的list和原来的temp
            helper(list.drop(1), temp, conditionSet, conditionEntry, result)
        }
    }

    fun <T : Any> getCombinations(lists: List<List<Set<T>>>,
                                  sort : (List<Set<T>>) -> Int = { it.stream().mapToInt { s -> s.size }.sum() },
                                  //一样也是升序排列
                                  ): List<List<Set<T>>> {
        // 从最外层的list里，每个组选一个set拿出，组成一个list，遍历，作为结果
        val result: MutableList<List<Set<T>>> = mutableListOf() // 存储最终结果
        generateCombinations(lists, result, 0, ArrayList()) // 调用递归方法
        return result.sortedBy(sort).distinct()
    }

    // 递归方法，用于生成所有可能的组合
    private fun <T> generateCombinations(
        lists: List<List<Set<T>>>,
        result: MutableList<List<Set<T>>>,
        depth: Int,
        current: MutableList<Set<T>>
    ) {
        if (depth == lists.size) { // 如果已经遍历完所有的list
            if (isValid(current)) { // 检查当前的组合是否有效（没有重复元素）
                result.add(ArrayList(current)) // 把当前的组合加入到结果中
            }
            return
        }
        for (i in lists[depth].indices) { // 遍历当前深度的list中的每个set
            current.add(lists[depth][i]) // 把当前的set加入到当前的组合中
            generateCombinations(lists, result, depth + 1, current) // 递归调用下一层深度的list
            current.removeAt(current.size - 1) // 回溯，移除当前的set
        }
    }

    // 检查一个list中的set是否有重复元素
    private fun <T> isValid(list: List<Set<T>>): Boolean {
        val set: MutableSet<T> = mutableSetOf() // 用一个set来存储所有元素
        list.forEach { s -> s.forEach {
            // 遍历list中的每个set
            t ->
            // 遍历set中的每个元素
            if (set.contains(t)) return false
            // 如果set已经包含了该元素，说明有重复，返回false
            else set.add(t)
            // 否则，把该元素加入到set中
        } }
        return true
    }


}