package org.co2dice.mirai.bean.card.api

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-03-10-9:07
 * @Message: 这个类是对公开的卡片使用的，墓地中的卡片，场上的卡片都需要这个类来获取一个独一无二的uuid，这个id一局游戏中只有一个，用来区分不同的卡片。
 **/
interface OpenCardInstance {
    val uniqueId :Int
}