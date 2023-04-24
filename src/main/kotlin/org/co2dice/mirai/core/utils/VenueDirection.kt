package org.co2dice.mirai.core.utils

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-16-0:17
 * @Message: 顺时针方向
 **/
enum class VenueDirection (val id : Int){
    UP(0),
    RIGHT_UP(1),
    RIGHT_DOWN (2),
    DOWN(3),
    LEFT_DOWN(4),
    LEFT_UP(5),
    STAY(-1),
    ;

    fun getDirection(direction : Int) : VenueDirection{
        return when(direction){
            0 -> UP
            1 -> RIGHT_UP
            2 -> RIGHT_DOWN
            3 -> DOWN
            4 -> LEFT_DOWN
            5 -> LEFT_UP
            else -> STAY
        }
    }
}