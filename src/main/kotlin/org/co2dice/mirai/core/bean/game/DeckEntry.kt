package org.co2dice.mirai.core.bean.game

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.prototype.ItemCard
import org.co2dice.mirai.core.bean.card.prototype.SkillCard
import org.co2dice.mirai.core.bean.card.prototype.VenueCard
import org.co2dice.mirai.core.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.ConstantUtils.CARD_LIMIT_MIN
import org.co2dice.mirai.core.utils.ConstantUtils.CARD_LIMIT_MAX

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-18-23:27
 * @Message: Have a good time!  :)
 **/
class DeckEntry(
    val commander : ChessmanEntry,
    val holder : PlayerInstance,
    val main : MutableList<CardEntry<*>>,
    val venue : MutableList<CardEntry<VenueCard>>
){

    fun getItemCards() : MutableList<CardEntry<ItemCard>>{
        return main.filterIsInstance<CardEntry<ItemCard>>().toMutableList()
    }

    fun checkDeckLegal() : Boolean{
        //检查卡组构建完成了没
        return main.size in CARD_LIMIT_MIN..CARD_LIMIT_MAX
    }

}