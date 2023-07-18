package org.co2dice.mirai.core.utils.dimension

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-02-17:20
 * {@code @Message:} Have a good time!  :)
 **/
data class Coordinate2D(val x : Int, val y : Int){
    companion object{
        fun center(size : Int) : Coordinate2D{
            return Coordinate2D(size/2,size/2)
        }
    }
}
