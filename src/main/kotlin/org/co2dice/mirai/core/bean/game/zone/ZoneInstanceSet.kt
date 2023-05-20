package org.co2dice.mirai.core.bean.game.zone

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

class ZoneInstanceSet(
    val registry : UniqueIdRegistry,
    var holder: PlayerInstance,
    deck : MutableList<CardEntry>,
    venueDeck : MutableList<CardEntry>,
    //主卡组的卡片列表,记录了entry的信息
    //领域归属于玩家,而不是棋子
) {
    //区域类，核心是玩家
    //这个类中所有的卡片都是依附于玩家的，而非棋子的，玩家不可为空


    private val buffer : BufferInstance = BufferInstance(holder = holder)
    //缓冲区，释放卡片时的区域，类比如万智牌的堆叠区域或者连锁处理区

    private val deck : DeckInstance = DeckInstance(
        holder = holder,
        //将主卡组的卡片列表传入,并转换为cardInstance
        cards = deck.map { it.toInstance(holder) }.toMutableList()
    )
    //主卡组

    private val venueDeck : VenueDeckInstance = VenueDeckInstance(
        holder = holder,
        cards = venueDeck.map { it.toInstance(holder) }.toMutableList())
    //场地卡组，放场地卡

    private val hand : StackZoneInstance = StackZoneInstance(holder = holder, cards = mutableListOf())
    //手牌

    private val gy : StackZoneInstance = StackZoneInstance(holder = holder, cards = mutableListOf())
    //墓地，放那些丢失的道具

    private val banish : StackZoneInstance = StackZoneInstance(holder = holder, cards = mutableListOf())
    //除外区，放损坏的道具



    fun start(){
        for (i in 0..4){
            try {
                deck.move(deck.getTopCard(),hand)
            }catch (e : NoSuchElementException){
                //卡组没有卡了
                noCardInDeck(deck,equipmentZone.keys)
                deck.move(deck.getTopCard(),hand)
            }
        }
    }

    fun noCardInDeck(deck: DeckInstance, chessmen:Set<ChessmanInstance>){
        //卡组没有卡了
        chessmen.forEach { chessman ->
            //获取每个chessman的token条，使其所有基础toKen-1点
            chessman.attributeTable.forEach { tokenFuller ->
                if (tokenFuller.removeToken(1) == false){
                    //token不足，棋子死亡
                    chessmanDie(chessman)
                }else{
                    //token足够，棋子不死亡,将墓地的所有卡放回卡组，如果墓地没卡，强制对所有棋子执行直死
                    if (!gy.moveAllCardTo(deck)){
                        chessmen.forEach{chessmanDie(it)}
                    }
                }
            }
        }
    }






}