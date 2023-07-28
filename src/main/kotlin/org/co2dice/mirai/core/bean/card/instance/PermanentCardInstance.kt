package org.co2dice.mirai.core.bean.card.instance

import kotlinx.serialization.Serializable
import org.co2dice.mirai.core.bean.api.PermanentInstance

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-21-12:05
 * {@code @Message:} 作为永久物的卡片，可以出现在场上的卡片，包括: 道具卡,场地卡
 * 不是所有公开的卡片都是永久物，比如墓地的卡片
 * 不是所有永久物都是"公开的卡片"，比如场地，徽记
 **/
@Serializable
sealed class PermanentCardInstance(
    //进场回合
) : PublicCardInstance(), PermanentInstance {
    open var entryRound : Int = 0
    open var tap : Boolean = false
    //卡是否被横置
}