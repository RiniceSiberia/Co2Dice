package org.co2dice.mirai.core.bean.effect.utils


import org.co2dice.mirai.core.bean.game.Scene
import org.co2dice.mirai.core.bean.api.Agent
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.bean.effect.EffectTargets
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.game.zone.ZoneInstanceSet
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

//定义一个具体的场景,一个效果处理时，会和场景中所有的对象进行交互。使用场景可以规范访问。
data class Situation(
//    val input : Map<String,Any>,
    //传参，这里记录的是经过翻译，从string转成object的传参，和target有部分关联
    val scene: Scene,
    //进行action的场景
    val agent: Agent,
    //进行action的目标卡
    val player: PlayerInstance,
    //进行action的玩家
    val initiator: ChessmanInstance?,
    //进行action的目标角色，角色控制卡
    val target: EffectTargets,
    //action的目标,封装类
    val effect: EffectEntry
    //action本身
){

    fun getActionHolderZone() : ZoneInstanceSet?{
        return scene.zones[player]
    }

}
