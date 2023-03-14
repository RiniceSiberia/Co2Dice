package org.co2dice.mirai.bean.zone

import org.co2dice.mirai.bean.Player
import org.co2dice.mirai.bean.card.entry.CardEntry
import org.co2dice.mirai.bean.chessman.entry.ChessmanEntry
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance
import org.co2dice.mirai.utils.FailException

class ZoneInstanceSet(
    var holder: Player?,
    deck : MutableList<CardEntry>,
    venueDeck : MutableList<CardEntry>,
    //主卡组的卡片列表,记录了entry的信息
    //领域归属于玩家,而不是棋子
    chessman: ChessmanEntry
) {
    init {
        createEquipmentZone(chessman.toInstance(holder))
    }

    val equipmentZone : MutableMap<ChessmanInstance, EquipmentsInstance> = mutableMapOf()
    //一个玩家可以拥有不止一个棋子，当失去所有棋子或所有棋子都无法行动时,游戏失败,除非是系统棋子
    //Equipments(装备）是和棋子绑定的

    private val buffer : BufferInstance = BufferInstance(holder = holder)
    //缓冲区，释放卡片时的区域，类比如万智牌的堆叠区域或者连锁处理区

    private val deck : DeckInstanceStack = DeckInstanceStack(
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

    fun noCardInDeck(deck: DeckInstanceStack, chessmen:Set<ChessmanInstance>){
        //卡组没有卡了
        chessmen.forEach { chessman ->
            //获取每个chessman的token条，使其所有基础toKen-1点
            chessman.attributeInstanceTable.forEach { tokenFuller ->
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

    fun chessmanDie(chessman: ChessmanInstance){
        chessman.die()
        //棋子死亡
        equipmentZone.remove(chessman)
        //检查是否还有棋子
        if (equipmentZone.isEmpty()){
            //没有棋子了，游戏失败
            throw FailException()
        }
    }

    fun createEquipmentZone(chessman: ChessmanInstance){
        //创建永久物区域
        equipmentZone[chessman] = EquipmentsInstance(chessman)
    }




}