package org.co2dice.mirai.bean.game

import org.co2dice.mirai.bean.cards.character.PlayerCharacterCard
import org.co2dice.mirai.bean.game.decorator.handler.DecoratorHolder
import org.co2dice.mirai.bean.game.zone.DeckInstanceStack

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:55
 * @Message: Have a good time!  :)
 **/
abstract class Scene (
    val decks:MutableMap<PlayerCharacterCard,DeckInstanceStack>,
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

    val damageList: MutableList<Damage> = mutableListOf()
    //卡组列表,开局时需要加载玩家的虚拟卡组进入这个类

    fun makeDamage(){

    }



}