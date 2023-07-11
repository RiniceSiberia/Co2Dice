package org.co2dice.mirai.core.bean.card.instance

import org.co2dice.mirai.core.bean.card.entry.CardEntry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-21-12:02
 * @Message: 公开的卡片实体，包括:
 * 墓地·场上·除外区的所有卡片
 * 发动中的卡片
 **/
sealed class PublicCardInstance(
    entry: CardEntry,
) : CardInstance(entry) {
}