package org.co2dice.mirai.bean.game

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.decorator.handler.DecoratorHolder
import org.co2dice.mirai.bean.zone.ZoneInstanceSet

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:55
 * @Message: Have a good time!  :)
 **/
abstract class Scene (
    val zones:MutableMap<Player, ZoneInstanceSet> = mutableMapOf(),
    //玩家与其相关区域的映射
    var hasEnded:Boolean,
    //是否已经结束
    var isClosed:Boolean
    //是否已经关闭（暂停）
): DecoratorHolder() {

    var map:Array<Array<*>> = arrayOf()

    val damageList: MutableList<Damage> = mutableListOf()
    //伤害列表

    fun makeDamage(){

    }




}