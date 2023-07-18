package org.co2dice.mirai.core.bean.card.instance

import org.co2dice.mirai.core.bean.api.CAO
import org.co2dice.mirai.core.bean.api.InstanceStructure
import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.prototype.EventCard
import org.co2dice.mirai.core.bean.card.prototype.ItemCard
import org.co2dice.mirai.core.bean.card.prototype.SkillCard
import org.co2dice.mirai.core.bean.card.prototype.VenueCard

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2022-12-05-22:58
 * {@code @Message:} 卡片实例
 **/
sealed class CardInstance(
    override val entry: CardEntry,
) :InstanceStructure<CardEntry>{
    fun sort() : Int{
        val prototype = entry.prototype
        //返回这张卡的排序优先级
        val type = when(prototype){
            is ItemCard -> 0
            is SkillCard -> 1
            is EventCard -> 2
            is VenueCard -> 4
            else -> 99
        }
        val chaos : Int = if (prototype is CAO) prototype.chaos ?: 0 else 99
        val order : Int = if (prototype is CAO) prototype.order ?: 0 else 99

        val cardName : Int =
            //将别名转换成为二位数的int，a最小,z最大
            entry.cardAlias.let {
                it[0].code * 100 + it[1].code
            }
        return type * 100000000 + chaos * 1000000 + order * 10000 + cardName
    }



}