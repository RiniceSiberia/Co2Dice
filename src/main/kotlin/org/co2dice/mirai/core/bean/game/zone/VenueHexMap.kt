package org.co2dice.mirai.core.bean.game.zone

import org.co2dice.mirai.core.bean.card.instance.venue.VenueCardInstance
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.utils.ConstantUtils
import org.co2dice.mirai.core.utils.VenueDirection
import org.co2dice.mirai.core.utils.VenueDirection.*
import kotlin.math.min

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-16-0:13
 * @Message: Have a good time!  :)
 **/
class VenueHexMap(private val size : Int = ConstantUtils.VENUE_SIZE_MAX) {
    private val venueMap = Array(size) { arrayOfNulls<VenueCardInstance>(size) }
    private val chessMap = Array(size) { arrayOfNulls<ChessmanInstance>(size) }
    //坐标系:左下角为0，右上角为满，x为横坐标，y为纵坐标
    //当x为奇数时，可以前往的地方:UP = [x+1][y],DOWN = [x-1][y],LEFT_UP = [x][y-1],LEFT_DOWN = [x][y+1],RIGHT_UP = [x+1][y-1],RIGHT_DOWN = [x+1][y+1]
    //当x为偶数时，

    fun addVenue(venue: VenueCardInstance, x: Int, y: Int) : Boolean {
        if (venueMap[x][y] != null) return false
        venueMap[x][y] = venue
        return true
    }

    fun addChessman(chessman: ChessmanInstance, x: Int, y: Int) : Boolean{
        if (venueMap[x][y] != null && venueMap[x][y]!!.volume > 0){
            chessMap[x][y] = chessman
            return true
        }
        chessman.disengagement()
        return false
    }

    fun getVenue(x: Int, y: Int): VenueCardInstance? {
        return venueMap[x][y]
    }

    fun getStepPosition(direction :VenueDirection, x: Int, y: Int) : Pair<Int,Int>{
        return when(direction){
            UP -> Pair(x,y+1)
            DOWN -> Pair(x,y-1)
            STAY -> Pair(x,y)
            LEFT_UP -> Pair(
                x-1,
                if (x % 2 == 0) {
                    y
                } else {
                    y+1
                }
            )
            LEFT_DOWN -> Pair(
                x-1,
                if (x % 2 == 0) {
                    y-1
                } else {
                    y
                }
            )
            RIGHT_UP -> Pair(
                x+1,
                if (x % 2 == 0) {
                    y
                } else {
                    y+1
                }
            )
            RIGHT_DOWN -> Pair(
                x+1,
                if (x % 2 == 0) {
                    y-1
                } else {
                    y
                }
            )
        }
    }


    fun step(direction :VenueDirection, x: Int, y: Int) : Pair<VenueCardInstance?,Pair<Int,Int>?>{
        val position = getStepPosition(direction, x, y)
        if (legalPosition(position.first, position.second)){
            return Pair(venueMap[position.first][position.second],position)
        }
        return Pair(null,null)
        //first空second不空是此处没地，双空是边界外
    }



    fun getVenuesByDirection(direction :VenueDirection, x: Int, y: Int): MutableList<VenueCardInstance> {
        //根据方向获取目标方向一条射线的场地,直到下标不合法
        val venues = mutableListOf<VenueCardInstance>()
        //使用step方法，反复调用，直到遇见边界
        var venue = step(direction, x, y)
        while (venue.second != null && legalPosition(venue.second!!.first, venue.second!!.second)){
            if (venue.first != null) venues.add(venue.first!!)
            venue = step(direction, venue.second!!.first, venue.second!!.second)
        }
        return venues
    }


    private fun legalPosition(x: Int, y: Int): Boolean {
        return x in 0 until size && y in 0 until size
    }

    fun getPosition(venue: VenueCardInstance): Pair<Int, Int>? {
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (venueMap[i][j] == venue) return Pair(i, j)
            }
        }
        return null
    }




}