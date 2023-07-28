package org.co2dice.mirai.core.bean.card.instance

import kotlinx.serialization.Serializable

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-04-21-12:02
 * {@code @Message:} 公开的卡片实体，包括:
 * 墓地·场上·除外区的所有卡片
 * 发动中的卡片
 **/
@Serializable
sealed class PublicCardInstance() : CardInstance() {
}