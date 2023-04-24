package org.co2dice.mirai.core.bean.emblem

import org.co2dice.mirai.core.bean.api.Agent
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.utils.Situation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-20-13:43
 * @Message: Emblem(徽记)来自于万智牌的徽记，他可以放在人物，物品，场地上。
 * 徽记是永存的，除非依附的卡离场或者依附的人物无法继续行动，它不会被清除。
 * 徽记可以释放效果。徽记释放的效果必须通过被依附的物体实现。
 * 徽记本身就是instance,不存在原型
 **/
class Emblem<T : Any>(val activeEffects: MutableMap<String, EffectEntry>) : Agent{


    fun invoke(effectId : String, situation: Situation, initiator : T){

    }

}