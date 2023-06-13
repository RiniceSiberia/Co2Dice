package org.co2dice.mirai.core.utils

import org.co2dice.mirai.core.bean.dice.entry.LotteryPool
import kotlin.reflect.KClass

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-15-22:10
 * @Message: Have a good time!  :)
 **/
class UniqueIdRegistry {
    private val map = mutableMapOf<KClass<*>, MutableSet<Int>>()

    //注册
    fun register(clazz: KClass<*>) :Int{
        return randomPut(clazz)
    }

    //注销
    fun cancel(clazz: KClass<*>, id: Int) : Boolean{
        return if (map[clazz] == null){
            false
        }else{
            map[clazz]!!.remove(id)
        }
    }

    //随机放数
    private fun randomPut(clazz: KClass<*>):Int{
        if (map[clazz] == null){
            map[clazz] = mutableSetOf()
        }
        val list = (0..9999).toMutableList()
        while (list.size > 0){
            val id = LotteryPool(list).roll()
            val set = map[clazz]!!
            if (set.contains(id)){
                list.remove(id)
                continue
            }else{
                set.add(id)
                return id
            }
        }
        throw IndexOutOfBoundsException("unique id fulled")
    }
}