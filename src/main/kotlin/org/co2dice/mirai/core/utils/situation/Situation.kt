package org.co2dice.mirai.core.utils.situation


import org.co2dice.mirai.core.ast.Params
import org.co2dice.mirai.core.bean.game.Scene
import org.co2dice.mirai.core.bean.api.Agent
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.bean.effect.EffectTargets
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.game.zone.ZoneInstanceSet
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance

//定义一个具体的场景,一个效果处理时，会和场景中所有的对象进行交互。使用场景可以规范访问。
//主类是最终的类,伴生对象中的PreActivation是发动前的类,Activation是发动时的类
data class Situation(
    val input : Map<String,Any>,
    //传参，这里记录的是经过翻译，从string转成object的传参，和target有部分关联
    val params : Params,
    val scene: Scene,
    //进行action的场景
    val agent: Agent<*>,
    //进行action的目标卡
    val player: PlayerInstance,
    //进行action的玩家
    val initiator: ChessmanInstance?,
    //进行action的目标角色，角色控制卡
    val target: EffectTargets,
    //action的目标,封装类
    val effect: EffectEntry<*>,
    //action本身
    val isActive : Boolean
){

    fun getActionHolderZone() : ZoneInstanceSet?{
        return scene.zones[player]
    }

    //发动前的情况记录，不记录具体的action对象
    data class PreActivation(
        val input : Map<String,Any>,
        val scene: Scene,
        val player: PlayerInstance,
    ){
        fun getActionHolderZone() : ZoneInstanceSet?{
            return scene.zones[player]
        }
    }

    data class Activation(
        val input : Map<String,Any>,
        val scene: Scene,
        val agent: Agent<*>,
        val player: PlayerInstance,
        val initiator: ChessmanInstance?,
        val target: EffectTargets,
        val effect: EffectEntry<*>,
        val isActive : Boolean,
    ){
        fun getActionHolderZone() : ZoneInstanceSet?{
            return scene.zones[player]
        }
    }

}
