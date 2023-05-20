package org.co2dice.mirai.core.bean.game

import org.co2dice.mirai.core.bean.game.time.Turns
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
    //角色和地形卡的地图
    val players:MutableList<PlayerInstance> = decks.keys.toMutableList(),
    //玩家的映射
    val zones:MutableMap<PlayerInstance, ZoneInstanceSet> = mutableMapOf<PlayerInstance, ZoneInstanceSet>()
        .apply { players.forEach { this[it] = ZoneInstanceSet(
            holder = it,
            deck = decks[it]!!.getMainCards(),
            venueDeck = decks[it]!!.getVenueCards()
        ) } },
    val venueMap: VenueMap = VenueMap(mapSize).apply {
        //从每个玩家的venueDeck中获取4张地形卡
        zones.forEach { it.ve }
    },
): DecoratorHolder() {




    val history :MutableList<ResolutionSituation> = mutableListOf()
    //历史记录列表

    fun makeDamage(){

    }


//    fun<E : Effect> createSituation(input : Map<String,Any>,
//                                    agent : Agent<E>,
//                                    player: PlayerInstance,
//                                    effect : EffectEntry<E>) : Situation {
//
//
//
//        return Situation(
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




}