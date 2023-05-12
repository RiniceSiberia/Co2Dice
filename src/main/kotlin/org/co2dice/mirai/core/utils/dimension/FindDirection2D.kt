package org.co2dice.mirai.core.utils

import org.co2dice.mirai.core.utils.dimension.Coordinate2D
import org.co2dice.mirai.core.utils.dimension.Direction2D
import org.co2dice.mirai.core.utils.dimension.Hexagon
import org.co2dice.mirai.core.utils.dimension.Square

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-16-0:17
 * @Message: 顺时针方向
 **/
object FindDirection2D {
    //这个对象的目的是要实现多边的方向构建

    fun <D : Direction2D>getDirection(direction : Int,list : List<D>) : D?{
        for (value in list){
            if (value.id == direction){
                return value
            }
        }
        return null
    }

    fun <D : Direction2D>getDirectionByPass(start : Coordinate2D,end : Coordinate2D,list : List<D>) : D?{
        //获取start的坐标，遍历func,如果end和获得的结果一样就返回
        for (value in list){
            if (value.func.invoke(start) == end){
                return value
            }
        }
        return null
    }

}