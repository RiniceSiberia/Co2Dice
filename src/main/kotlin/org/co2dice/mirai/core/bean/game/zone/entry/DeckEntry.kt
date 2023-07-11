package org.co2dice.mirai.core.bean.game.zone.entry

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.prototype.ItemCard
import org.co2dice.mirai.core.bean.card.prototype.SkillCard
import org.co2dice.mirai.core.bean.card.prototype.VenueCard
import org.co2dice.mirai.core.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.core.bean.player.enrty.PlayerEntry
import org.co2dice.mirai.core.utils.ConstantUtils.CARD_LIMIT_MAX
import org.co2dice.mirai.core.utils.ConstantUtils.CARD_LIMIT_MIN

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-04-18-23:27
 * @Message: 卡组实体,游戏外的存储数据
 **/
class DeckEntry(
    val holder : PlayerEntry,
    val chessmen : MutableList<ChessmanEntry>,
    val main : MutableList<CardEntry>,
    val venueCard : MutableList<CardEntry>
) {

    constructor(holder: PlayerEntry, chessmen: MutableList<ChessmanEntry>, cards : MutableList<CardEntry>) : this(
        holder, chessmen,
        cards.filter { it.prototype is ItemCard || it.prototype is SkillCard }.toMutableList(),
        cards.filter { it.prototype is VenueCard }.toMutableList(),
    )


    fun getItemCards() : MutableList<CardEntry>{
        return main.filter { it.prototype is ItemCard }.toMutableList()
    }

    fun checkDeckLegal() : Boolean{
        //检查卡组构建完成了没
        return main.size in CARD_LIMIT_MIN..CARD_LIMIT_MAX
    }

}