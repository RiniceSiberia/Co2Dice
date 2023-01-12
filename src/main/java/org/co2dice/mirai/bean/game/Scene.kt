package org.co2dice.mirai.bean.game

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.cards.character.CharacterCard
import org.co2dice.mirai.bean.game.Damage
import org.co2dice.mirai.bean.game.decorator.handler.DecoratorHolder

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:55
 * @Message: Have a good time!  :)
 **/
abstract class Scene (
    val characters: MutableList<CharacterCard>,
    val damageList: MutableList<Damage>,
    //伤害缓存列表
    var hasEnded:Boolean,
    //是否已经结束
    var isClosed:Boolean
    //是否已经关闭（暂停）
): DecoratorHolder() {
    //玩家列表，包括kp和所有参与者。
//    val buffList = mutableListOf<AbstractBuff>()
    //buff列表
    //角色列表，会存储不同类型的角色



    fun makeDamage(){

    }



}