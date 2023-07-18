package org.co2dice.mirai.core.bean.game.zone.api

import org.co2dice.mirai.core.bean.card.instance.CardInstance

/**
 *      使用IDEA编写
 * {@code @Author:} DUELIST
 * {@code @Time:}  2023-06-15-0:14
 * {@code @Message:} Have a good time!  :)
 **/
interface CardVesselApi<C : CardInstance> {
    fun checkCard(function: (C) -> Boolean):Boolean

    fun contain(card: CardInstance):Boolean{
        return checkCard { it == card }
    }
    fun removed(card: CardInstance):Boolean

    fun addCard(card: C):Boolean

}