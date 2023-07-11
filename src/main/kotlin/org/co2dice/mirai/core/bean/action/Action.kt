package org.co2dice.mirai.core.bean.action

import org.co2dice.mirai.core.utils.situation.PreActivationSituation
import org.co2dice.mirai.core.utils.situation.ResolutionSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-06-24-16:37
 * @Message: 所有行动的接口
 * 行动包括:
 * 攻击Attack
 * 移动Movement
 * 棋子召唤Summon
 * 棋子撤退Retreat
 * 启动式异能Activate
 * 触发式效果Trigger
 * 注 : 这个类不能给释放中的卡
 * 释放的卡本身不会进入联锁 只有战吼会进入联锁
 **/
interface Action {
    fun launchConditions(situation: PreActivationSituation) : Boolean
    //发动所需条件
    fun targetFunction() : Boolean
    //行动的目标
    fun operation(situation: ResolutionSituation) : Boolean
}