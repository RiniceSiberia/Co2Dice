package org.co2dice.mirai.bean.tokens

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-07-12:52
 * @Message: Have a good time!  :)
 **/
interface TempToken {
    var lifeTime:Int
    fun checkPoint():Boolean

    fun timeCheck():Boolean{
        if (checkPoint()){
            return timeFlow()
        }
        return false
    }

    private fun timeFlow():Boolean{
        lifeTime--
        return lifeTime <= 0
    }
}