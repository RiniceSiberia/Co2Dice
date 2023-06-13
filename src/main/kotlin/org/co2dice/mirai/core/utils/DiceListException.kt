package org.co2dice.mirai.core.utils

import org.co2dice.mirai.core.bean.dice.SampleSpace
import kotlin.streams.toList

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-13-23:51
 * @Message: Have a good time!  :)
 **/
class DiceListException (
    ){
    fun <T: Any,K : Any>exception(spaces : List<SampleSpace<T>>,
                                  toKey : (List<T>) -> K, )  : MutableMap<K,Int>{
        val possibilityMap : MutableMap<K,Int> = mutableMapOf()
        val now : MutableList<Int> = spaces.stream().mapToInt { 0 }.toList().toMutableList()
        val limit : List<Int> = spaces.stream().mapToInt { it.list.size-1 }.toList().toMutableList()
        while (limit != now){
            calculate(toKey,spaces,possibilityMap,now,limit)
        }
        return possibilityMap
    }

    fun <T: Any,K : Any>calculate(
        toKey : (List<T>) -> K,
        spaces : List<SampleSpace<T>>,
        possibilityMap : MutableMap<K,Int>,
        now : MutableList<Int>,
        limit: List<Int>){
        //首先提取每个spaces中下标和和now一致的元素,然后组成list
        var index = 0
        val thisResult : List<T> = spaces.stream().map {
            it.list[now[index]].also { index++ } }.toList()
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

object test {
    fun foo() {

    }
}