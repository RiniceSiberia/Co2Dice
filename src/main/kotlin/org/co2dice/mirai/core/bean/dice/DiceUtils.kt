package org.co2dice.mirai.core.bean.dice

import org.apache.commons.statistics.distribution.ContinuousDistribution

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-22-12:57
 * @Message: Have a good time!  :)
 **/
object DiceUtils {
}

fun<T> Map<T,Int>.roll() : T{
    return toList().random()
}

fun<T> Map<T,Int>.toList() : List<T>{
    val list = mutableListOf<T>()
    this.forEach { (t, u) ->
        for (i in 0 until u){
            list.add(t)
        }
    }
    return list
}

fun List<Int>.toDice () : Map<Int,Int> {
    return this.groupingBy { it }.eachCount()
}

fun <T : Any> ContinuousDistribution.getDispersedSpace(
    min : Double,
    max : Double,
    mapping : (Double) -> T) : Map<T,Int> {
    val space = this.getDispersedSpace( min, max)
    val newSpace = mutableMapOf<T,Int>().apply {
        space.forEach { (k, v) ->
            if (this[mapping(k)] == null) this[mapping(k)] = 0
            this[mapping(k)] = this[mapping(k)]!! + v
        }
    }
    return newSpace
}

fun ContinuousDistribution.getDispersedSpace(
                      min : Double = 0.0,
                      max : Double,
                      precision : Int = (max - min).toInt()) : Map<Double, Int> {
    val t = this
    //默认精度为取整数
    val map : Map<Double,Double> = mutableMapOf<Double, Double>().apply {
        //截取min到max中precision段
        val step = (max - min) / precision
        var current = min
        repeat(precision){
            this[current] = t.density(current)
            current += step
        }
    }
    //获取其中的最小值，将其除以1
    val minDensity = map.values.minOrNull()
    return if (minDensity == null) {
        mapOf()
    }else{
        val divider = 1.0 / minDensity
        map.mapValues { (_, v) ->
            (v * divider).toInt()
        }
    }
}

fun Map<Int, Int>.getDiceName(): String {
    if (this.isEmpty()) return "Null"

    val max = this.keys.max()
    if (this.keys.size == 1) return max.toString()

    return if (this.keys.all { it in 1..max && this[it] == this[1] }) {
        "d$max"
    } else {
        "[${this.toList().sorted().joinToString { "," }}]"
    }
}


