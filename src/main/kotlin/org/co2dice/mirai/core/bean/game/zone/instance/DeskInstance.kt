package org.co2dice.mirai.core.bean.game.zone.instance

import org.co2dice.mirai.core.bean.card.entry.CardEntry
import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.game.zone.api.CardVesselApi
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.utils.UniqueIdRegistry

class DeskInstance(
    private val registry : UniqueIdRegistry,
    val holder: PlayerInstance,
    deck : MutableList<CardEntry>,
    venueDeck : MutableList<CardEntry>,
    //主卡组的卡片列表,记录了entry的信息
    //领域归属于玩家,而不是棋子
) {
    //区域类，核心是玩家
    //这个类中所有的卡片都是依附于玩家的，而非棋子的，玩家不可为空


    val buffer : SpellCardStack = SpellCardStack(holder = holder)
    //缓冲区，释放卡片时的区域，类比如万智牌的堆叠区域或者连锁处理区

    val deck : DeckInstance = DeckInstance(
        holder = holder,
        //将主卡组的卡片列表传入,并转换为cardInstance
        cards = deck.mapNotNull { it.initializeDeckInstance(holder) }.toMutableList()
    )
    //主卡组

    val venueDeck : VenueDeckInstance = VenueDeckInstance(
        holder = holder,
        cards = venueDeck.mapNotNull { it.initializeVenueInstance(holder) }.toMutableList())
    //场地卡组，放场地卡

    val hand : HandInstance = HandInstance(holder = holder)
    //手牌

    val gy : GyZoneInstance = GyZoneInstance(holder)
    //墓地，放那些丢失的道具,以及使用过的技能

    val banish : BanishZoneInstance = BanishZoneInstance(holder = holder)
    //除外区，放损坏的道具，和遗忘的技能，还有使用过的角色卡

    fun draw() : Boolean{
        val card = deck.draw() ?: return false
        if (!hand.add(card)){
            //手牌满了，爆牌,具体规则以后写
        }
        return true
    }

    fun noCardInDeck(){
        //卡组没有卡了
//        chessmen.forEach { chessman ->
//            //获取每个chessman的token条，使其所有基础toKen-1点
//            chessman.attributeTable.forEach { tokenFuller ->
//                if (tokenFuller.removeToken(1) == false){
//                    //token不足，棋子死亡
//                    chessmanDie(chessman)
//                }else{
//                    //token足够，棋子不死亡,将墓地的所有卡放回卡组，如果墓地没卡，强制对所有棋子执行直死
//                    if (!gy.moveAllCardTo(deck)){
//                        chessmen.forEach{chessmanDie(it)}
//                    }
//                }
//            }
//        }
    }

    fun moveCard(card : CardInstance, from : CardVesselApi<*>, to : CardListVessel<*>,) : Boolean{
        //将卡片从一个区域移动到另一个区域
        if (from.removed(card)){
            return addCard(to,card)
        }
        return false
    }
    fun addCard(to : CardListVessel<*>, card : CardInstance) :Boolean{
        if (zoneAffiliatedThis(to)){
            return to.addCard(card.entry,registry)
        }
        return false
    }

    fun findCardInstance(card : CardInstance) : CardVesselApi<*>?{
        //找到卡片所在的区域
        return when{
            deck.contain(card) -> deck
            venueDeck.contain(card) -> venueDeck
            hand.contain(card) -> hand
            gy.contain(card) -> gy
            banish.contain(card) -> banish
            else -> null
        }
    }

    fun zoneAffiliatedThis(zone : CardListVessel<*>) : Boolean{
        //判断区域是否属于这个玩家
        return zone == buffer || zone == deck || zone == venueDeck || zone == hand || zone == gy || zone == banish
    }






}