package org.co2dice.mirai.core.utils.dimension

import java.util.*
import kotlin.collections.ArrayList

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-05-10-14:06
 * {@code @Message:} Have a good time!  :)
 **/
class DirectionsPool (
    directions : List<Direction2D>
): ArrayList<Direction2D>(directions) {
    //统一采用右上正，左下负的坐标系

    fun getAntiDirection(direction: Direction2D,test: Coordinate2D = Coordinate2D(1,1)) : Direction2D?{
        //获取反方向
        val nextC = direction.getStepPosition(test)
        for (d in this){
            if (d.getStepPosition(nextC) == test) return d
        }
        return null
    }

    companion object{
        enum class DirectionName{
            //只表示name，
            UP,DOWN,
            LEFT,RIGHT,
            RIGHT_UP,RIGHT_DOWN,LEFT_UP,LEFT_DOWN,
            ;
            fun getLowercase() : String{
                //返回小写
                return this.name.lowercase(Locale.getDefault())
            }
            companion object{
                fun getById(id : Int) : DirectionName{
                    return values()[id]
                }
                fun getLowNameById(id : Int) : String{
                    return getById(id).getLowercase()
                }
            }
        }

        fun square() : DirectionsPool{
            return DirectionsPool(listOf(
                Direction2D(DirectionName.UP.getLowercase()) { Coordinate2D(it.x, it.y + 1) },
                Direction2D(DirectionName.DOWN.getLowercase()) { Coordinate2D(it.x, it.y - 1) },
                Direction2D(DirectionName.LEFT.getLowercase()) { Coordinate2D(it.x - 1, it.y) },
                Direction2D(DirectionName.RIGHT.getLowercase()) { Coordinate2D(it.x + 1, it.y) }
            ))
        }
        fun hexagon() : DirectionsPool{
            //竖六边形
            return DirectionsPool(listOf(
                Direction2D(DirectionName.UP.getLowercase()) { Coordinate2D(it.x, it.y + 1) },
                Direction2D(DirectionName.DOWN.getLowercase()) { Coordinate2D(it.x,it.y-1)},
                Direction2D(DirectionName.RIGHT_UP.getLowercase()) { Coordinate2D(it.x+1,if (it.x % 2 == 0) it.y else it.y+1)},
                Direction2D(DirectionName.RIGHT_DOWN.getLowercase()) { Coordinate2D(it.x+1,if (it.x % 2 == 0) it.y-1 else it.y)},
                Direction2D(DirectionName.LEFT_UP.getLowercase()) { Coordinate2D(it.x-1,if (it.x % 2 == 0) it.y else it.y+1)},
                Direction2D(DirectionName.LEFT_DOWN.getLowercase()) { Coordinate2D(it.x-1,if (it.x % 2 == 0) it.y-1 else it.y)}
            ))
        }

        fun octagon() : DirectionsPool{
            return DirectionsPool(listOf(
                Direction2D(DirectionName.UP.getLowercase()) { Coordinate2D(it.x, it.y + 1) },
                Direction2D(DirectionName.DOWN.getLowercase()) { Coordinate2D(it.x,it.y-1)},
                Direction2D(DirectionName.LEFT.getLowercase()) { Coordinate2D(it.x-1,it.y)},
                Direction2D(DirectionName.RIGHT.getLowercase()) { Coordinate2D(it.x+1,it.y)},
                Direction2D(DirectionName.RIGHT_UP.getLowercase()) { Coordinate2D(it.x+1,it.y+1)},
                Direction2D(DirectionName.RIGHT_DOWN.getLowercase()) { Coordinate2D(it.x+1,it.y-1)},
                Direction2D(DirectionName.LEFT_UP.getLowercase()) { Coordinate2D(it.x-1,it.y-1)},
                Direction2D(DirectionName.LEFT_DOWN.getLowercase()) { Coordinate2D(it.x-1,it.y+1)}
            ))
        }
    }

}