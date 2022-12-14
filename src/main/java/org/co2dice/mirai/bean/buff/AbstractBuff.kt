package org.co2dice.mirai.bean.buff

import org.co2dice.mirai.bean.battle.Scene
import org.co2dice.mirai.bean.cards.api.RelyToCard

/**
  * @author 韩左券
  * @date 2022/12/12 14:29
  * @input
  * @return_value
  * @message buff是一个添加在token，道具，技能或场景上的效果，用于提供一个永续的效果。比如受伤减少，攻击力增加等等。
  * @log /
  */
abstract class AbstractBuff : RelyToCard {
    abstract val id : String
    abstract var name : String
    //buff的名称和id
    abstract val description : String
    //buff的描述
    abstract val trigger:Function2<Scene,AbstractBuff,Boolean>
    //触发开关
    abstract val effect:Function2<Scene,AbstractBuff,Boolean>
    //永续效果
    abstract val params:MutableMap<String,Int>
    //buff的参数
    abstract val addEvent:Function2<Scene,AbstractBuff,Boolean>
    //buff的添加事件
    abstract val removeEvent:Function2<Scene,AbstractBuff,Boolean>
    //buff的移除事件

}