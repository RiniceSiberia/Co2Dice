package org.co2dice.mirai.bean.game


import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONB
import com.alibaba.fastjson2.JSONObject
import com.alibaba.fastjson2.annotation.JSONField
import kotlinx.serialization.json.JsonObject
import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.utils.SerializeJSON
import java.util.*

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-25-15:13
 * @Message: Have a good time!  :)
 **/
class GameSession (
    @JSONField(name = "UUID")
    var sessionId:UUID = UUID.randomUUID(),
    //	创建会话时自动分配的会话 ID。 当访问存储在会话中的信息时，此值通常包含在 URI 中。
    @JSONField(name = "rosters")
    var rosters: MutableList<Player> = mutableListOf(),
    //会话中玩家的数组。 名单包含当前玩家以及之前在会话中但已离开的玩家。 名单中的玩家顺序永远不会改变。 新玩家将添加到数组的末尾。
    @JSONField(name = "creationTime")
    var creationTime:Calendar = Calendar.getInstance(),
    //会话创建的日期和时间。
//    private val customData: MutableList<Player> = mutableListOf(),
    @JSONField(name = "displayName")
    var displayName:String,
    //游戏会话的显示名称，最长 128 个字符。
    @JSONField(name = "hasEnded")
    var hasEnded:Boolean = false,
    //如果会话已结束为 true，否则为 false。 将此字段设置为 true 会将会话标记为只读，防止其他数据被提交到会话。
    @JSONField(name = "isClosed")
    var isClosed:Boolean = false,
    //如果会话关闭且再没有可以添加的玩家则为 true，否则为 false。 如果此值为 true，请求加入会话将被拒绝。
    @JSONField(name = "maxPlayer")
    var maxPlayer:Int = 16,
    //可同时处于会话中的玩家的最大数。 值范围为 2-16。 一旦会话包含的玩家达到最大数，其他加入会话的请求将被拒绝。
//    var playersCanBeRemovedBy:PlayersRemovedEnum = ,
//    //谁可以踢掉其他玩家
//    var titleId:Int = 0,
//    //游戏会话的标题 ID。 此值对服务器是不透明的。
    @JSONField(name = "visibility")
    var visibility: VisibilityLevelEnum = VisibilityLevelEnum.PRIVATE,
    //能如何访问这个会话
    @JSONField(name = "password")
    var password:String? = null,
    //密码，如果有密码非空则必须要输入密码才能加入游戏，和会话无关
    @JSONField(name = "dependId")
    var dependId:String?,
    //决定这个对话是否有一个公屏来显示公开信息展示公开信息，为空则是向所有参与者的qq对话框中发公开信息
    @JSONField(name = "sceneList")
    var sceneList:MutableList<Scene> = mutableListOf()
    //场景列表,用来记录每一幕场景中的信息，场景均可转为json字符串存储。
    // 场景是线性式的，里面有自由场景，战斗场景，交流场景，侦查场景，一场场景结束前，另一场场景不会开始。
    // 当一个场景结束时，会生成一个新场景。新的场景会被添加到场景列表的末尾。只有末尾的场景才能进行操作。
    ) {

    var seatsAvailable:Int = maxPlayer - rosters.size

    enum class VisibilityLevelEnum{
        //本游戏的会话有:
        // 公开(可以在群组或私聊中进行游戏，可以通过指令获取会话列表)，
        // 私有(可以在群组或私聊中进行游戏，只有输入会话的id才能加入)，
        // 匹配(可以随机匹配的加入其他人的游戏，只能私聊中进行,这个类型的游戏会话只会有一个场景)
        PUBLIC, PRIVATE, MATCH
    }

}