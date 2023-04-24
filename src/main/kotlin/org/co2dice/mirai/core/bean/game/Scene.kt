package org.co2dice.mirai.core.bean.game

import org.co2dice.mirai.core.bean.api.Agent
import org.co2dice.mirai.core.bean.effect.entry.EffectEntry
import org.co2dice.mirai.core.bean.effect.utils.Situation
import org.co2dice.mirai.core.bean.game.zone.VenueHexMap
import org.co2dice.mirai.core.bean.game.zone.ZoneInstanceSet
import org.co2dice.mirai.core.bean.player.instance.PlayerInstance
import org.co2dice.mirai.core.decorator.handler.DecoratorHolder
import org.co2dice.mirai.core.utils.ConstantUtils
import org.co2dice.mirai.core.utils.UniqueIdRegistry

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2022-12-08-20:55
 * @Message: Have a good time!  :)
 **/
abstract class Scene (
    val players:MutableList<PlayerInstance>,
    decks :Map<PlayerInstance,DeckEntry>,
    val zones:MutableMap<PlayerInstance, ZoneInstanceSet> = mutableMapOf<PlayerInstance, ZoneInstanceSet>()
        .apply { players.forEach { this[it] = ZoneInstanceSet(holder = it, deck = ) } },
    //玩家与其相关区域的映射
    var hasEnded:Boolean,
    //是否已经结束
    var isClosed:Boolean,
    //是否已经关闭（暂停）
    mapSize:Int = ConstantUtils.VENUE_SIZE_MAX,
    val venueMap: VenueHexMap = VenueHexMap(mapSize)
): DecoratorHolder() {
    //一個六边形地图

    val registry : UniqueIdRegistry = UniqueIdRegistry()

    val damageList: MutableList<Damage> = mutableListOf()
    //伤害列表

    val history :MutableList<Situation> = mutableListOf()
    //历史记录列表

    fun makeDamage(){

    }


    fun createSituation(input : Map<String,Any>, agent : Agent, player: PlayerInstance, effect : EffectEntry) : Situation{



        return Situation(
            input = input,
            scene = this,
            agent = agent,
            player = player,
            initiator = ,
            target = ,
            effect = effect,

        )
    }




}