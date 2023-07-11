package org.co2dice.mirai.core.ast

import org.co2dice.mirai.core.utils.situation.SituationApi
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-06-22:10
 * @Message: 存放参数的类
 **/
data class Params (
    val map : MutableMap<String, Any> = mutableMapOf(),
    val situation : SituationApi,
    ){

    //强转param类型
    @SuppressWarnings("unchecked","unsafe")
    @Suppress("UNCHECKED_CAST")
    fun<O> get(key : String) : O{
        val out = map[key] as O
        return out!!
    }
    fun inputLegal(target : Map<String, KClass<*>>) : Boolean{
        //将target进行stream化，如果key都对得上且对应key的value，每个map的类型是value的子类或者本类，返回true
        //允许param中存在target中不存在的key
        return target.entries.stream().allMatch {
            map.containsKey(it.key) && it.value.isInstance(map[it.key])
        }
    }
    fun add(key : String, value : Any){
        map[key] = value
    }

    fun remove(key : String) : Any?{
        return map.remove(key)
    }


}