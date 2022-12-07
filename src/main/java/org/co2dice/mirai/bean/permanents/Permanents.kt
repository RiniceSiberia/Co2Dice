package org.co2dice.mirai.bean.permanents

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-23:36
 * @Message: Have a good time!  :)
 **/
abstract class Permanents {
    abstract val id:String
    abstract var lifeTime:Int
    fun isTemp():Boolean{
        return lifeTime > 0
    }
    fun timeFlow(){
        lifeTime--
    }
}