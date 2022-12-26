package org.co2dice.mirai.bean.game

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.buff.AbstractBuff
import org.co2dice.mirai.bean.cards.character.CharacterCard

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:55
 * @Message: Have a good time!  :)
 **/
open class Scene (open val players: MutableList<Player> = mutableListOf()){
    //玩家列表，包括kp和所有参与者。
    val damageList = mutableListOf<Damage>()
    //伤害缓存列表
    val buffList = mutableListOf<AbstractBuff>()
    //buff列表
    val characters = mutableSetOf<CharacterCard>()
    //角色列表，会存储不同类型的角色




    fun makeDamage(){

    }


}