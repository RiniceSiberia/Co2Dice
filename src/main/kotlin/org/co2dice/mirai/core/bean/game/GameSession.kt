package org.co2dice.mirai.core.bean.game


import org.co2dice.mirai.core.bean.game.zone.ZoneInstanceSet
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-25-15:13
 * @Message: Have a good time!  :)
 **/
class GameSession (

    var sessionId:String = UUID.randomUUID().toString(),
    //	创建会话时自动分配的会话 ID。 当访问存储在会话中的信息时，此值通常包含在 URI 中。
    var rosters: MutableList<PlayerInstance> = mutableListOf(),
    //会话中玩家的数组。 名单包含当前玩家以及之前在会话中但已离开的玩家。 名单中的玩家顺序永远不会改变。 新玩家将添加到数组的末尾。

    var creationTime:Calendar = Calendar.getInstance(),
    //会话创建的日期和时间。
//    private val customData: MutableList<Player> = mutableListOf(),

    var displayName:String,
    //游戏会话的显示名称，最长 128 个字符。
    var maxPlayer:Int = 16,
    //可同时处于会话中的玩家的最大数。 值范围为 2-16。 一旦会话包含的玩家达到最大数，其他加入会话的请求将被拒绝。
//    var playersCanBeRemovedBy:PlayersRemovedEnum = ,
//    //谁可以踢掉其他玩家
//    var titleId:Intelligence = 0,
//    //游戏会话的标题 ID。 此值对服务器是不透明的。
    var visibility: VisibilityLevelEnum = VisibilityLevelEnum.PRIVATE,
    //能如何访问这个会话
    var password:String? = null,
    //密码，如果有密码非空则必须要输入密码才能加入游戏，和会话无关
    var dependId:String? = null,
    //决定这个对话是否有一个公屏来显示公开信息展示公开信息，为空则是向所有参与者的qq对话框中发公开信息
    private val sceneList:MutableList<Scene> = mutableListOf()
    //场景列表,用来记录每一幕场景中的信息，场景均可转为json字符串存储。
    // 场景是多元的，里面有自由场景，战斗场景，交流场景，侦查场景。
    // 一个玩家可以加入一个场景，但不可以加入多个场景。会话中的玩家是可以随便加入一个场景的,除非他已经在另一个场景中
    // 场景可以同时存在多个。但一个人只能在一个场景里。
    ) {

    fun getSeatsAvailable():Int{
        return maxPlayer - rosters.size
    }
    //可进入的玩家数量
    var hasEnded:Boolean = false
    //如果会话已结束为 true，否则为 false。 将此字段设置为 true 会将会话标记为只读，防止其他数据被提交到会话。
    var isClosed:Boolean = false
    //如果会话关闭且再没有可以添加的玩家则为 true，否则为 false。 如果此值为 true，请求加入会话将被拒绝。

    //新建一场战斗，需要：
    //1：初始的玩家，玩家的数量不能小于1
    //2：每个玩家的卡组和场地卡组和棋子
    fun addBattle(map:MutableMap<PlayerInstance, ZoneInstanceSet>):Boolean{
        //使用一些map来创建一个Sence
        //1:判定该角色是否是本会话中的角色
        map.keys.forEach{
            if (it !in rosters){
                return false
            }
        }
        //2:如果是本绘画的角色，判定该角色是否在其他场景里
        //现在,一个player只能在一个场景里,所以这里不需要判定角色的chessman了
        map.keys.forEach{
            if (it in sceneList.map {i -> i.zones.keys }.flatten()){
                return false
            }
        }
        //新建一个场景
        val battle = Battle(map)
        return sceneList.add(battle)
    }

    fun getPlayerScene(player: PlayerInstance):Scene?{
        //获取玩家所在的场景
        return sceneList.stream()
            .filter { player in it.zones.keys }
            .findFirst().orElse(null)
    }




    enum class VisibilityLevelEnum{
        //本游戏的会话有:
        // 公开(可以在群组或私聊中进行游戏，可以通过指令获取会话列表)，
        // 私有(可以在群组或私聊中进行游戏，只有输入会话的id才能加入)，
        // 匹配(可以随机匹配的加入其他人的游戏，只能私聊中进行,这个类型的游戏会话只会有一个场景)
        PUBLIC, PRIVATE, MATCH
    }

}