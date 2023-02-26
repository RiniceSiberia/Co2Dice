package org.co2dice.mirai.bean.game.zone

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.game.entry.card.CardEntry
import org.co2dice.mirai.bean.game.prototype.character.Chessman

class ZoneInstanceSet(
    var holder: Player?,
    deck : MutableList<CardEntry>,
    venueDeck : MutableList<CardEntry>,
    //主卡组的卡片列表,记录了entry的信息
    //领域归属于玩家,而不是棋子
    chessman: Chessman
) {
    val permanents : MutableMap<Chessman,PermanentsInstance>
    = mutableMapOf(chessman to PermanentsInstance(chessman = chessman))
    //一个玩家可以拥有不止一个棋子，当失去所有棋子或所有棋子都无法行动时,游戏失败,除非是系统棋子
    //permanents(永久物）是和棋子绑定的

    val buffer : BufferInstance = BufferInstance(holder = holder)
    //缓冲区，释放卡片时的区域，类比如万智牌的堆叠区域或者连锁处理区

    val deck : DeckInstanceStack = DeckInstanceStack(
        holder = holder,
        //将主卡组的卡片列表传入,并转换为cardInstance
        cards = deck.map { it.toInstance(holder) }.toMutableList()
    )
    //主卡组

    val venueDeck : VenueDeckInstance = VenueDeckInstance(
        holder = holder,
        cards = venueDeck.map { it.toInstance(holder) }.toMutableList())
    //场地卡组，放场地卡

    val hand : HandInstanceStack = HandInstanceStack(holder = holder)
    //手牌

    val gy : GYInstanceStack = GYInstanceStack(holder = holder)
    //墓地

}