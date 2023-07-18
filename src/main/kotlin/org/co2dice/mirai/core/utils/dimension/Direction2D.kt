package org.co2dice.mirai.core.utils.dimension

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-02-22:06
 * {@code @Message:} 基础类,2D的方向都要能通过这个来获取下一步的坐标
 **/
open class Direction2D(val name : String, val func : (Coordinate2D) -> Coordinate2D){
    fun getStepPosition(x: Int, y: Int) : Coordinate2D{
        return func.invoke(Coordinate2D(x,y))
    }

    fun getStepPosition(coordinate2D: Coordinate2D) : Coordinate2D{
        return func.invoke(coordinate2D)
    }

    fun getStepPosition(coordinate2D: Coordinate2D, step : Int) : Coordinate2D{
        var temp = coordinate2D
        for (i in 0 until step){
            temp = func.invoke(temp)
        }
        return temp
    }
}