package org.co2dice.mirai.core.bean.card.instance.venue


import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.api.PermanentCardInstance
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.utils.UniqueIdRegistry

class VenueCardInstance (
    entry: CardEntry,
    registry : UniqueIdRegistry,
    val volume :Int = 8,
    //场地可容纳棋子数
    //举例说明，宽敞的大平原可容纳的只有2个棋子，而狭窄的山谷可容纳的棋子只有1~2个
    //如果场地容纳棋子数为0，那么该场地就是一个障碍物，棋子不能进入。如果强制进入，那么这个棋子就会直接死亡。
    var activeEffects: MutableList<EffectEntry>,
) : PermanentCardInstance(entry,registry)  {


    fun canPassArrow (): Boolean{
        return true
        //先写true，以后如果出现被动效果是无法通过箭头的，再改
    }

}