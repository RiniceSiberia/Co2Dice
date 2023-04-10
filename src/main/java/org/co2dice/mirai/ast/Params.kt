package org.co2dice.mirai.ast

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-06-22:10
 * @Message: Have a good time!  :)
 **/
class Params (
    private val map : Map<String, Any>
    ){
    //强转param类型
    @SuppressWarnings("unchecked","unsafe")
    @Suppress("UNCHECKED_CAST")
    fun<O> get(key : String) : O{
        val out = map[key] as O
        return out!!
    }
}