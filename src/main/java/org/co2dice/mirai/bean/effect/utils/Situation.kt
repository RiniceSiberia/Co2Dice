package org.co2dice.mirai.bean.effect.utils


import org.co2dice.mirai.bean.card.instance.CardInstance
import org.co2dice.mirai.bean.game.Scene
import org.co2dice.mirai.bean.api.EffectAPI
import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.bean.effect.EffectTargets
import org.co2dice.mirai.bean.zone.EquipmentsInstance
import org.co2dice.mirai.bean.zone.ZoneInstanceSet

//定义一个具体的场景,一个效果处理时，会和场景中所有的对象进行交互。使用场景可以规范访问。
data class Situation(
    val input : Map<String,Any>,
    //传参，这里记录的是经过翻译，从string转成object的传参，和target有部分关联
    val scene: Scene,
    //进行action的场景
    val cards: CardInstance,
    //进行action的目标卡
    val player: Player,
    //进行action的玩家
    val chessman : ChessmanInstance?,
    //进行action的目标角色，角色控制卡
    val target : EffectTargets,
    //action的目标,封装类
    val effect : EffectAPI
    //action本身
){

    fun getActionHolderZone() : ZoneInstanceSet?{
        return scene.zones[player]
    }

}
