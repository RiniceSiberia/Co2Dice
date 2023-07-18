package org.co2dice.mirai.core.utils

import org.co2dice.mirai.core.bean.dice.entry.DispersedSpace
import kotlin.streams.toList

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-13-23:51
 * {@code @Message:} Have a good time!  :)
 **/
object DiceListExpectant{
    /*
    * 所有可能性的穷举，value是该可能性的出现次数
     */
    fun <T: Any,K : Any>exhaustive(spaces : List<DispersedSpace<T>>,
                                   toKey : (List<T>) -> K, )  : Map<K,Int>{
        val possibilityMap : MutableMap<K,Int> = mutableMapOf()
        val now : MutableList<Int> = spaces.stream().mapToInt { 0 }.toList().toMutableList()
        val limit : List<Int> = spaces.stream().mapToInt { it.toList().size-1 }.toList().toMutableList()
        var i = 0
        while (limit != now){
            i++
            calculate(toKey,spaces,possibilityMap,now,limit)
            if (i > 14285714){
                //随便写的，防止死循环
                return emptyMap()
            }
        }
        //现在获得了所有的结果
        return possibilityMap.toMap()
    }


    /**
 *
 * 算特定几种情况的概率
 **/
    fun <T: Any,K : Any>probability(
    spaces : List<DispersedSpace<T>>,
    toKey : (List<T>) -> K,
    filter : (K) -> Boolean = {true}) : Map<K,Double>{
        val expectant = exhaustive(spaces,toKey)
        val sum = expectant.values.sum()
    //所有情况的总数和
        return expectant.mapValues { (key, value) ->
            value.toDouble() / sum
        }.filter { (key, _) ->
            filter(key)
        }
    }

/**
* IntSampleSpace专用
 * 方差计算和平均数计算
 * 默认是给N个Int样本空间中抽出的数值求和
**/
    fun variance(
    spaces : List<DispersedSpace<Int>>,
    toKey : (List<Int>) -> Int = { it.sum() },
    ) : Map<Int,Int>{
        val expectant = exhaustive(spaces,toKey)
        //获取期望值后，计算方差
        val average = average(expectant)
        return mutableMapOf<Int, Int>().apply {
            expectant.forEach { (key, _) ->
                this[key] = (key - average) * (key - average)
            }
        }
    }

    fun average(
        spaces: List<DispersedSpace<Int>>,
        toKey: (List<Int>) -> Int = { it.sum() },) : Int{
        return average(exhaustive(spaces,toKey))
    }

    fun average(expectant: Map<Int, Int>): Int {
        return expectant.map { it.key * it.value }.sum() / (expectant.values.sum())
    }

    private fun <T: Any,K : Any>calculate(
        toKey : (List<T>) -> K,
        spaces : List<DispersedSpace<T>>,
        possibilityMap : MutableMap<K,Int>,
        now : MutableList<Int>,
        limit: List<Int>){
        return calculate(toKey,spaces.map { it.toList() },possibilityMap,now,limit)
    }


    private fun <T: Any,K : Any>calculate(
        toKey : (List<T>) -> K,
        spaces : List<List<T>>,
        possibilityMap : MutableMap<K,Int>,
        now : MutableList<Int>,
        limit: List<Int>){
        //首先提取每个spaces中下标和和now一致的元素,然后组成list
        var index = 0
        val thisResult : List<T> = spaces.stream().map {
            it[now[index]].also { index++ }
        }.toList()
        val key = toKey(thisResult)
        if (possibilityMap[key] == null){
            possibilityMap[key] = 0
        }
        possibilityMap[key] = possibilityMap[key]!! + 1
        var i = 0
        while (i < now.size){
            now[i]++
            if (now[i] > limit[i]){
                now[i] = 0
                i++
            }else{
                break
            }
        }
    }
}