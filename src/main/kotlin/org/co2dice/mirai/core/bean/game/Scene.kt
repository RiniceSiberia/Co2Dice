package org.co2dice.mirai.core.bean.game

import org.co2dice.mirai.core.bean.card.instance.CardInstance
import org.co2dice.mirai.core.bean.card.instance.ItemCardInstance
import org.co2dice.mirai.core.bean.card.instance.VenueCardInstance
import org.co2dice.mirai.core.bean.game.time.Turns
import org.co2dice.mirai.core.bean.game.zone.CardsVessel
import org.co2dice.mirai.core.bean.game.zone.VenueMap
import org.co2dice.mirai.core.bean.game.zone.ZoneInstanceSet
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.decorator.handler.DecoratorHolder
import org.co2dice.mirai.core.utils.ConstantUtils
import org.co2dice.mirai.core.utils.UniqueIdRegistry
import org.co2dice.mirai.core.utils.situation.ResolutionSituation

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:55
 * @Message: Have a good time!  :)
 **/
abstract class Scene (
    val registry : UniqueIdRegistry = UniqueIdRegistry(),
    //依赖于场景的注册器
    decks :Map<PlayerInstance,DeckEntry>,
    //玩家与其相关区域的映射
    var hasEnded:Boolean,
    //是否已经结束
    var isClosed:Boolean,
    //是否已经关闭（暂停）
    mapSize:Int = ConstantUtils.VENUE_SIZE_MAX,
    val turns: Turns = Turns(),
    //回合
    val zones:MutableMap<PlayerInstance, ZoneInstanceSet> = mutableMapOf<PlayerInstance, ZoneInstanceSet>()
        .apply { decks.keys.filter { decks[it]?.checkDeckLegal()?:false }.forEach { this[it] = ZoneInstanceSet(
            registry = registry,
            holder = it,
            deck = decks[it]!!.main,
            venueDeck = decks[it]!!.venue
        ) } },
    //卡组不合法直接禁止游戏
    val venueMap: VenueMap = VenueMap(mapSize).apply {
        //从每个玩家的venueDeck中获取4张地形卡
        zones.forEach {
            val list : MutableList<VenueCardInstance> = mutableListOf()
            for (i in 0 until 3){
                val unPublicVenue = it.value.venueDeck.draw()
                if (unPublicVenue != null){
                    list.add(
                        VenueCardInstance(
                        entry = unPublicVenue.entry ,
                        registry = registry )
                    )
                }
            }
        }
    },
    //角色和地形卡的地图
): DecoratorHolder() {

    init {
        if (zones.size < 2){
            throw Exception("玩家数量不足")
        }
        if (zones.size != decks.size){
            throw Exception("有玩家的卡组不合法")
        }
    }


    val history :MutableList<ResolutionSituation> = mutableListOf()
    //历史记录列表

    fun makeDamage(){

    }


    fun start() : Boolean{
        //开启场景，所有角色抽5张卡,如果有人卡组有问题，返回false
        getPlayers().forEach{
            for (i in 0 until  4){
                val deckNotEmpty = zones[it]?.draw() ?: return false
                //如果false
                if (!deckNotEmpty){
                    //如果卡组空了
                }
            }
        }.also { return true }
    }

    fun draw(player : PlayerInstance) : Boolean{
        val deckNotEmpty = zones[player]?.draw() ?: return false
        if (!deckNotEmpty){
            //如果卡组空了,对一个玩家的每个棋子的每个属性都削弱一个单位。如果基础的六个属性中,有一个属性没有被

        }
    }

    fun moveCard(card : CardInstance<*>,to : CardsVessel<*,*>) : Boolean{
        if (card is ItemCardInstance){
            val equipments = venueMap.chessMap.values.stream().map { it.equipments }.toList()
            if (equipments.stream().anyMatch { it.getAllEquip().contains(card) }){
                //from于战场,
                var wasRemoved = false
                equipments.stream().forEach {
                    wasRemoved = it.remove(card) || wasRemoved
                }
                return !wasRemoved
                    && zones[card.holder?:return false]?.moveCard()
                .remove(card)?:false
            }
        }

    }


//    fun<E : Effect> createSituation(input : Map<String,Any>,
//                                    agent : Agent<E>,
//                                    player: PlayerInstance,
//                                    effect : EffectEntry<E>) : BaseSituationSymbol {
//
//
//
//        return BaseSituationSymbol(
//            input = input,
//            scene = this,
//            agent = agent,
//            player = player,
//            initiator = ,
//            target = ,
//            effect = effect,
//
//        )
//    }

    private fun getPlayers() : List<PlayerInstance>{
        return zones.keys.toList()
    }
}