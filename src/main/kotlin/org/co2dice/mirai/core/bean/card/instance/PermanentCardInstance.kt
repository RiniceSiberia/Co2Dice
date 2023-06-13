package org.co2dice.mirai.core.bean.card.instance

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.PublicCardInstance
import org.co2dice.mirai.core.bean.effect.prototype.field.FieldEffect
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-21-12:05
 * @Message: Have a good time!  :)
 **/
sealed class PermanentCardInstance(
    entry: CardEntry<*>,
    registry : UniqueIdRegistry,
    var entryRound : Int = 0,
    //进场回合
) : PublicCardInstance<FieldEffect>(entry,registry){
    var faceUp : Boolean = true
    //装备卡是否正面朝上
    var tap : Boolean = false
    //卡是否被横置
}