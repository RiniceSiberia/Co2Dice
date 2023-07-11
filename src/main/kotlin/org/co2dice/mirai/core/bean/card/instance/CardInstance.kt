package org.co2dice.mirai.core.bean.card.instance

import org.co2dice.mirai.core.bean.api.InstanceStructure
import org.co2dice.mirai.core.bean.card.entry.CardEntry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-05-22:58
 * @Message: 卡片实例
 **/
sealed class CardInstance(
    override val entry: CardEntry,
) :InstanceStructure<CardEntry>{
}