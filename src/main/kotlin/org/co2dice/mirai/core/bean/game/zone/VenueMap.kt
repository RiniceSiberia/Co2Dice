package org.co2dice.mirai.core.bean.game.zone

import org.checkerframework.common.value.qual.IntRange
import org.co2dice.mirai.core.bean.card.instance.venue.VenueCardInstance
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.utils.ConstantUtils.TINY_FUSE
import org.co2dice.mirai.core.utils.ConstantUtils.VENUE_REGULARITY_DEFAULT_RATE
import org.co2dice.mirai.core.utils.ConstantUtils.VENUE_SIZE_MAX
import org.co2dice.mirai.core.utils.FindDirection2D
import org.co2dice.mirai.core.utils.dimension.Coordinate2D
import org.co2dice.mirai.core.utils.dimension.Direction2D
import org.co2dice.mirai.core.utils.dimension.DirectionsPool

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-16-0:13
 * @Message: 地图类
 **/
class VenueMap(private val size : @IntRange(from = 0, to = VENUE_SIZE_MAX.toLong())Int = VENUE_SIZE_MAX,
               //尺寸是固定的，永远是正方的
               private val directions : DirectionsPool = DirectionsPool.square(),
               //方向池,这个方向决定了地图的边数,目前允许四边形,六边形和八边形chunk的地图
               private val regularityRate : Double = VENUE_REGULARITY_DEFAULT_RATE
) {
    private val chunks : Array<Array<VenueCardInstance?>> = Array(size) { arrayOfNulls(size) }
    //区块，类似mc的区块，每个区块都是一个地形卡
    private val chessMap : MutableMap<ChessmanInstance, ChessmanValue> = mutableMapOf()
    //一个玩家可以拥有不止一个棋子，当失去所有棋子或所有棋子都无法行动时,游戏失败,除非是系统棋子
    //Equipments(装备）是和棋子绑定的

    fun randomChunks(venues : List<VenueCardInstance>) : VenueMap{
        //随机生成一个被venues中的地形卡铺满的,地形卡完全连续，且中间没有空隙的地图
        //首先确定地图的中心坐标
        val randoms = venues.toMutableList().apply { shuffle() }
        if (randoms.isNotEmpty()){
            if (chunks.isEmpty()) addVenue(randoms.removeFirst(), Coordinate2D.center(size))
            while (randoms.isNotEmpty()){
                for (d in directions){
                    val sideChunk = getSide(d)
                    //获取边上()的区块,沿着这个方向走一步
                    val stepC = sideChunk.stream(). map { d.getStepPosition(it) }.toList()
                    for (c in stepC){
                        //如果这个区块没有被占用,就放一个地形卡
                        if (getVenue(c) == null && randoms.isNotEmpty() && Math.random() < regularityRate){
                            addVenue(randoms.removeFirst(), c)
                        }
                    }
                    if (randoms.isEmpty()) break
                }
            }
        }
        return this
    }

    fun randomChess(chessmen : List<ChessmanInstance>) : VenueMap{
        val mutable = chessmen.toMutableList()
        var blowout = 0
        while (mutable.isNotEmpty() && blowout < TINY_FUSE){
            val c = getRandomCoordinate()
            if (venueCanStand(c) && addChessman(mutable.removeFirst(), c)){
                blowout++
            }
        }
        return this
    }


    fun addVenue(venue: VenueCardInstance, coordinate: Coordinate2D) : Boolean {
        if (getVenue(coordinate) != null || legalPosition(coordinate)) return false
        chunks[coordinate.x][coordinate.y] = venue
        return true
    }

    fun addChessman(chessman: ChessmanInstance, coordinate: Coordinate2D) : Boolean{
        if (venueCanStand(coordinate)){
            chessMap[chessman] = ChessmanValue(coordinate, Equipments(chessman, mutableMapOf()))
            return true
        }
        return false
    }

    fun addChessman(chessman: ChessmanInstance, x : Int , y : Int) : Boolean{
        return addChessman(chessman, Coordinate2D(x,y))
    }

    fun getVenue(coordinate: Coordinate2D) : VenueCardInstance?{
        return if (legalPosition(coordinate)) chunks[coordinate.x][coordinate.y] else null
    }

    fun step(direction : Direction2D, coordinate : Coordinate2D, check : (Pair<VenueCardInstance, Coordinate2D>) -> Boolean)
    : Pair<VenueCardInstance?, Coordinate2D?>{
        val position = direction.getStepPosition(coordinate)
        //获取坐标
        if (legalPosition(position)){
            //如果坐标合法
            val venue =  getVenue(position)
            //获取场地
            return if (venue != null && check(Pair(venue,position))){
                //如果场地不为空且通过检查
                Pair(venue,position)
            }else{
                Pair(null,position)
            }
            //first空second不空是此处没地or没通过检查，双空是边界外
        }
        return Pair(null,null)
    }

    fun getVenuesByDirection(direction : Direction2D, coordinate: Coordinate2D, check : (Pair<VenueCardInstance, Coordinate2D>) -> Boolean): List<VenueCardInstance> {
        //根据方向获取目标方向一条射线的场地,直到下标不合法,或者遇见了没地的格子
        val venues = mutableListOf<VenueCardInstance>()
        //使用step方法，反复调用，直到遇见边界
        var venue = step(direction, coordinate,check)
        //先走一步
        while (venue.first != null && venue.second != null && legalPosition(venue.second!!)){
            //若下标合法
            venues.add(venue.first!!)
            // 如果此处有地,加入列表
            venue = step(direction, venue.second!!,check)
            //再走一步，直到前方没有地
        }
        return venues.distinct()
    }

    fun dfsVenues(start: Coordinate2D,
                  s : @IntRange(from = 0,to = VENUE_SIZE_MAX.toLong()) Int = VENUE_SIZE_MAX,
                  check : (Pair<VenueCardInstance, Coordinate2D>) -> Boolean,
                  visited : MutableSet<Coordinate2D> = mutableSetOf())
    : List<Pair<VenueCardInstance, Coordinate2D>> {
        //深度优先搜索，通过递归step方法获取到所有可达的场地
        //x和y代表初始坐标，step代表可行走的步数,check代表行走时的检查函数
        var step = s
        //可走的剩余步数，用var是为了可变
        val venues = mutableListOf<Pair<VenueCardInstance, Coordinate2D>>()
        //可达的场地列表
        if (step == 0) return venues else step--
        //如果剩余步数为0，返回空列表,否则走一步
        for (direction in directions){
            //遍历所有方向
            val venue = step(direction, start) { pair -> !visited.contains(pair.second) && check(pair)}
            //获取下一步的场地
            if (venue.first != null && venue.second != null){
                visited.add(venue.second!!)
                //如果此处有地,且坐标合法
                venues.add(Pair(venue.first!!,venue.second!!))
                //加入列表
                venues.addAll(dfsVenues(venue.second!!,step,check,visited))
                //递归获取下一步的场地
            }
        }
        return venues.distinctBy{it.second}

    }

    fun chessWalk(chess : ChessmanInstance,trail : List<Coordinate2D>)
        : List<Pair<VenueCardInstance, Coordinate2D>>{
        //根据痕迹,让chessman按照指定的路径走,路上要经过所有的格子
        val venues = mutableListOf<Pair<VenueCardInstance, Coordinate2D>>()
        for (i in 0 until trail.size - 1){
            //遍历痕迹
            val direction = FindDirection2D.getDirectionByPass(trail[i],trail[i+1],directions) ?: return emptyList()
            //获取方向,如果方向不存在或者为stay，就直接返回空列表
            val step = step(direction,trail[i])
            { venueCanStand(it.second) }
            //检查场地是否合乎要求，当前的要求是能站人，如果不能站人直接否决
            //获取下一步的坐标
            if(step.first != null && step.second != null && chessMove(step.second!!,chess)){
                //如果走到了下一步
                venues.add(Pair(step.first!!,step.second!!))
                //加入列表
            }else{
                return venues
                //如果没走到下一步，直接返回当前列表
            }
        }
        return venues
    }

    private fun getPosition(venue: VenueCardInstance): Pair<Int, Int>? {
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (chunks[i][j] == venue) return Pair(i, j)
            }
        }
        return null
    }

    private fun chessMove(coordinate: Coordinate2D, chessman: ChessmanInstance) : Boolean{
        //移动棋子(一次性移动)
        return venueCanStand(coordinate)
            //目标可去
            && chessMap[chessman]?.let { it.coordinate != coordinate } ?: false
            //为空时返回false,不为空时坐标不相同返回true,相同返回false
            && chessMap[chessman].also { it!!.coordinate = coordinate } != null
        //移动棋子
    }


    private fun legalPosition(coordinate: Coordinate2D): Boolean {
        //判断指定位置是否合法
        return coordinate.x in 0 until size && coordinate.y in 0 until size
    }

    private fun venueCanStand(coordinate: Coordinate2D) : Boolean{
        //判断指定位置是否可以站人
        return getVenue(coordinate) != null
            && (getVenue(coordinate)!!.volume - getVenueChessNum(coordinate)) > 0
    }

    private fun venueCanPassArrow(coordinate: Coordinate2D) : Boolean{
        //判断指定位置是否可以通过飞行道具
        return getVenue(coordinate) != null
            && getVenue(coordinate)!!.canPassArrow()
    }

    private fun getVenueChessNum(coordinate: Coordinate2D) : Int{
        //获取指定位置的棋子数量
        return chessMap.values.stream().filter { it.coordinate == coordinate }.count().toInt()
    }

    private fun getRandomCoordinate() : Coordinate2D{
        //获取随机坐标
        return Coordinate2D((0 until size).random(),(0 until size).random())
    }

    private fun getSide(direction: Direction2D) : List<Coordinate2D> {
        //如果区块数小于等于1，直接返回所有场地
        if (chunks.size <= 1) return getNotNullCoordinate()
        //获取array中所有对边没有场地，但对边反方向有场地的卡
        val result = mutableListOf<Coordinate2D>()
        for (coordinate in getNotNullCoordinate()){
            //遍历所有不为空的坐标
            val nextC = direction.getStepPosition(coordinate)
            //获取下一步的坐标
            if (legalPosition(nextC) && getVenue(nextC) == null){
                //如果下一步合法且为空
                val antiD = directions.getAntiDirection(direction,coordinate)
                //获取反方向
                if (antiD != null){
                    //如果反方向不为空
                    val antiNextC = antiD.getStepPosition(coordinate)
                    //获取反方向的下一步
                    if (getVenue(antiNextC) != null){
                        //如果反方向的下一步不为空
                        result.add(coordinate)
                        //返回反方向的下一步
                    }
                }
            }
        }
        return result
    }

    private fun getNotNullCoordinate() : List<Coordinate2D>{
        //获取array中所有不为空的坐标
        val coordinates = mutableListOf<Coordinate2D>()
        for (i in 0 until size){
            for (j in 0 until size){
                if (getVenue(Coordinate2D(i,j)) != null) coordinates.add(Coordinate2D(i,j))
            }
        }
        return coordinates
    }

    class ChessmanValue(var coordinate: Coordinate2D,val equipments: Equipments){
        //棋子在棋盘里的数据，包括坐标和装备栏
    }
}