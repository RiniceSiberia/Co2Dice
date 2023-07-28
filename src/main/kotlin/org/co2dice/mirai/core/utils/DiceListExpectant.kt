package org.co2dice.mirai.core.utils

import org.co2dice.mirai.core.utils.MathUtils.calculatePermutationsAndCount

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-13-23:51
 * {@code @Message:} Have a good time!  :)
 **/
object DiceListExpectant{
    fun <T: Any,K : Any>exhaustive(spaces : List<Map<T,Int>>,
                                   toKey : (List<T>) -> K )  : Map<K,Int>{
        return calculatePermutationsAndCount(spaces.map { it.keys.toList() },toKey)
    }


    /**
 *
 * 算特定几种情况的概率
 **/
    fun <T: Any,K : Any>probability(
    spaces : List<Map<T,Int>>,
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
    spaces : List<Map<Int,Int>>,
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
        spaces: List<Map<Int,Int>>,
        toKey: (List<Int>) -> Int = { it.sum() },) : Int{
        return average(exhaustive(spaces,toKey))
    }

    fun average(expectant: Map<Int, Int>): Int {
        return expectant.map { it.key * it.value }.sum() / (expectant.values.sum())
    }





}