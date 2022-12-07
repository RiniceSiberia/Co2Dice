package org.co2dice.mirai.bean.tokens

import org.co2dice.mirai.bean.cards.CardType

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-06-23:36
 * @Message: 衍生物类
 * 衍生物是一种类似于属性和指示物的东西，它可以根据类型的不同放置在卡上，用来表示一些状态。
 **/
interface Token {
    val id:String
    val name:String
    val type:CardType
    fun addEvent(fuller: TokenFuller):Boolean
    fun deleteEvent(fuller: TokenFuller):Boolean
}