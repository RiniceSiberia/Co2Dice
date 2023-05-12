package org.co2dice.mirai.core.bean.card.instance.api

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.effect.prototype.ActiveEffect
import org.co2dice.mirai.core.bean.effect.prototype.Effect
import org.co2dice.mirai.core.bean.effect.prototype.PassiveEffect
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-21-12:05
 * @Message: Have a good time!  :)
 **/
abstract class PermanentCardInstance<E : Effect>(
    entry: CardEntry,
    registry : UniqueIdRegistry,
    var entryRound : Int,
    //进场回合
) :PublicCardInstance<E>(entry,registry){
    var faceUp : Boolean = true
    //装备卡是否正面朝上
    var tap : Boolean = false
    //卡是否被横置
}